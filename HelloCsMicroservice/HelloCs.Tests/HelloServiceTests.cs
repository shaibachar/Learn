using HelloCs.Services;
using Xunit;

namespace HelloCs.Tests
{
    public class HelloServiceTests
    {
        [Fact]
        public void SayHello_ReturnsHelloWorld()
        {
            // Arrange
            var service = new HelloService();

            // Act
            var result = service.SayHello();

            // Assert
            Assert.Equal("Hello, World!", result);
        }
    }
}
