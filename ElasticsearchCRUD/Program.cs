using System;
using System.Threading.Tasks;
using Nest;

namespace ElasticsearchCRUD
{
    public class Person
    {
        public string Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public int Age { get; set; }
    }

    class Program
    {
        private static readonly string Uri = "http://10.0.0.19:9200";
        private static readonly string IndexName = "people";
        private static ElasticClient client;
        static async Task Main(string[] args)
        {
            var settings = new ConnectionSettings(new Uri(Uri)).DefaultIndex(IndexName);
            client = new ElasticClient(settings);

            await CreateIndexIfNotExists();

            var person = new Person { Id = Guid.NewGuid().ToString(), FirstName = "John", LastName = "Doe", Age = 30 };
            await CreatePersonAsync(person);

            // Wait briefly to ensure Elasticsearch has indexed the document
            await Task.Delay(1000);

            var readPerson = await GetPersonAsync(person.Id);

            if (readPerson != null)
            {
                Console.WriteLine($"Retrieved: {readPerson.FirstName} {readPerson.LastName}, Age: {readPerson.Age}");
            }
            else
            {
                Console.WriteLine("Person not found!");
            }

            person.Age = 31;
            await UpdatePersonAsync(person);

            await DeletePersonAsync(person.Id);
        }


        static async Task CreateIndexIfNotExists()
        {
            var existsResponse = await client.Indices.ExistsAsync(IndexName);
            if (!existsResponse.Exists)
            {
                await client.Indices.CreateAsync(IndexName, c => c
                    .Map<Person>(m => m.AutoMap())
                );
            }
        }

        static async Task CreatePersonAsync(Person person)
        {
            var response = await client.IndexDocumentAsync(person);
            Console.WriteLine(response.IsValid ? "Person created successfully." : $"Failed: {response.ServerError}");
        }

        static async Task<Person> GetPersonAsync(string id)
        {
            var response = await client.GetAsync<Person>(id);
            return response.Found ? response.Source : null;
        }

        static async Task UpdatePersonAsync(Person person)
        {
            var response = await client.UpdateAsync<Person>(person.Id, u => u.Doc(person));
            Console.WriteLine(response.IsValid ? "Person updated successfully." : $"Failed: {response.ServerError}");
        }

        static async Task DeletePersonAsync(string id)
        {
            var response = await client.DeleteAsync<Person>(id);
            Console.WriteLine(response.IsValid ? "Person deleted successfully." : $"Failed: {response.ServerError}");
        }
    }
}
