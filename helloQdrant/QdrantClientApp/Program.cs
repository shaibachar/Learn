using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

class Program
{
    static readonly HttpClient client = new HttpClient();
    const string baseUrl = "http://10.0.0.19:6333";
    const string collectionName = "sentence-collection";

    static List<(int Id, string Text, float[] Embedding)> sentences = new()
    {
        (1, "Artificial intelligence is changing the world.", new float[] { 0.91f, 0.87f, 0.89f, 0.88f, 0.9f }),
        (2, "Machine learning is a subfield of AI.", new float[] { 0.89f, 0.86f, 0.88f, 0.87f, 0.91f }),
        (3, "Cats are independent animals.", new float[] { 0.1f, 0.3f, 0.2f, 0.25f, 0.15f }),
        (4, "Dogs are loyal and friendly.", new float[] { 0.12f, 0.28f, 0.19f, 0.22f, 0.16f }),
        (5, "Neural networks power many AI tools.", new float[] { 0.88f, 0.9f, 0.87f, 0.86f, 0.92f }),
        (6, "The sun is a giant ball of gas.", new float[] { 0.4f, 0.2f, 0.1f, 0.3f, 0.2f }),
        (7, "Space exploration leads to scientific discoveries.", new float[] { 0.35f, 0.25f, 0.3f, 0.28f, 0.4f }),
        (8, "AI can generate images and text.", new float[] { 0.9f, 0.85f, 0.86f, 0.84f, 0.87f }),
        (9, "My dog enjoys playing fetch.", new float[] { 0.14f, 0.29f, 0.2f, 0.21f, 0.18f }),
        (10, "The Milky Way contains billions of stars.", new float[] { 0.36f, 0.27f, 0.29f, 0.31f, 0.38f })
    };

    static float[] queryEmbedding = new float[] { 0.9f, 0.86f, 0.87f, 0.88f, 0.89f };
    static string queryText = "How is AI used in the modern world?";

    static async Task Main(string[] args)
    {
        await CreateCollection();
        await InsertSentences();
        await Search();
    }

    static async Task CreateCollection()
    {
        // Force delete existing collection
        await client.DeleteAsync($"{baseUrl}/collections/{collectionName}");

        var payload = new
        {
            vectors = new
            {
                size = 5,
                distance = "Cosine"
            }
        };

        var response = await client.PutAsync(
            $"{baseUrl}/collections/{collectionName}",
            new StringContent(JsonConvert.SerializeObject(payload), Encoding.UTF8, "application/json"));

        Console.WriteLine($"Create Collection: {response.StatusCode}");
    }

    static async Task InsertSentences()
    {
        var points = new List<object>();
        foreach (var s in sentences)
        {
            points.Add(new
            {
                id = s.Id,
                vector = s.Embedding,
                payload = new { text = s.Text }
            });
        }

        var payload = new { points };

        var response = await client.PutAsync(
            $"{baseUrl}/collections/{collectionName}/points",
            new StringContent(JsonConvert.SerializeObject(payload), Encoding.UTF8, "application/json"));

        Console.WriteLine($"Insert Sentences: {response.StatusCode}");
    }

    static async Task Search()
    {
        var payload = new
        {
            vector = queryEmbedding,
            top = 5,
            with_payload = true
        };

        var response = await client.PostAsync(
            $"{baseUrl}/collections/{collectionName}/points/search",
            new StringContent(JsonConvert.SerializeObject(payload), Encoding.UTF8, "application/json"));

        var resultJson = await response.Content.ReadAsStringAsync();

        Console.WriteLine($"\n🔍 Raw JSON Response:\n{resultJson}");

        var result = JsonConvert.DeserializeObject<QdrantSearchResponse>(resultJson);

        if (result?.result == null)
        {
            Console.WriteLine("⚠️ No results found or unexpected response format.");
            return;
        }

        Console.WriteLine($"\n🔍 Search Results for: \"{queryText}\"");
        foreach (var item in result.result)
        {
            Console.WriteLine($"[Score: {item.score:F3}] {item.payload.text}");
        }
    }


    // --- Response DTOs ---
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
