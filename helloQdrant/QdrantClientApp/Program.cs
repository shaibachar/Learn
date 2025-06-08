using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

class Program
{
    static readonly HttpClient client = new HttpClient();
    const string qdrantUrl = "http://10.0.0.19:6333";
    const string collectionName = "sentence-collection";
    const string embeddingServerUrl = "http://10.0.0.19:8000";

    static List<(int Id, string Text)> sentences = new()
    {
        (1, "Artificial intelligence is changing the world."),
        (2, "Machine learning is a subfield of AI."),
        (3, "Cats are independent animals."),
        (4, "Dogs are loyal and friendly."),
        (5, "Neural networks power many AI tools."),
        (6, "The sun is a giant ball of gas."),
        (7, "Space exploration leads to scientific discoveries."),
        (8, "AI can generate images and text."),
        (9, "My dog enjoys playing fetch."),
        (10, "The Milky Way contains billions of stars.")
    };

    static string queryText = "How is AI used in the modern world?";

    static async Task Main(string[] args)
    {
        await CreateCollection();
        await InsertSentences();
        await Search();
    }

    static async Task<List<float>> GetEmbedding(string sentence)
    {
        var payload = new { sentences = new[] { sentence } };
        var json = JsonConvert.SerializeObject(payload);

        var response = await client.PostAsync(
            $"{embeddingServerUrl}/embed",
            new StringContent(json, Encoding.UTF8, "application/json"));

        response.EnsureSuccessStatusCode();
        var responseJson = await response.Content.ReadAsStringAsync();

        var result = JsonConvert.DeserializeObject<EmbeddingResponse>(responseJson);
        return result.embeddings.First();
    }

    static async Task CreateCollection()
    {
        await client.DeleteAsync($"{qdrantUrl}/collections/{collectionName}");

        var payload = new
        {
            vectors = new
            {
                size = 384,
                distance = "Cosine"
            }
        };

        var response = await client.PutAsync(
            $"{qdrantUrl}/collections/{collectionName}",
            new StringContent(JsonConvert.SerializeObject(payload), Encoding.UTF8, "application/json"));

        Console.WriteLine($"Create Collection: {response.StatusCode}");
    }

    static async Task InsertSentences()
    {
        var points = new List<object>();

        foreach (var (id, text) in sentences)
        {
            var embedding = await GetEmbedding(text);
            points.Add(new
            {
                id,
                vector = embedding,
                payload = new { text }
            });
        }

        var payload = new { points };

        var response = await client.PutAsync(
            $"{qdrantUrl}/collections/{collectionName}/points",
            new StringContent(JsonConvert.SerializeObject(payload), Encoding.UTF8, "application/json"));

        Console.WriteLine($"Insert Sentences: {response.StatusCode}");
    }

    static async Task Search()
    {
        var embedding = await GetEmbedding(queryText);

        var payload = new
        {
            vector = embedding,
            top = 5,
            with_payload = true
        };

        var response = await client.PostAsync(
            $"{qdrantUrl}/collections/{collectionName}/points/search",
            new StringContent(JsonConvert.SerializeObject(payload), Encoding.UTF8, "application/json"));

        var resultJson = await response.Content.ReadAsStringAsync();
        Console.WriteLine($"\n🔍 Raw JSON Response:\n{resultJson}");

        var result = JsonConvert.DeserializeObject<QdrantSearchResponse>(resultJson);

        if (result?.result == null)
        {
            Console.WriteLine("⚠️ No results found or unexpected response format.");
            return;
        }

        Console.WriteLine($"\n🔍 Top Matches for: \"{queryText}\"");
        foreach (var item in result.result)
        {
            Console.WriteLine($"[Score: {item.score:F3}] {item.payload.text}");
        }
    }

    // --- Response DTOs ---
    class EmbeddingResponse
    {
        public List<List<float>> embeddings { get; set; }
    }

    class QdrantSearchResponse
    {
        public List<QdrantPoint> result { get; set; }
    }

    class QdrantPoint
    {
        public float score { get; set; }
        public Payload payload { get; set; }
    }

    class Payload
    {
        public string text { get; set; }
    }
}
