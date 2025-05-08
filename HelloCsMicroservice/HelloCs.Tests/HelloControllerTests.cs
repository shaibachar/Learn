using HelloCs.Controllers;
using HelloCs.Services;
using Microsoft.AspNetCore.Mvc;
using Moq;
using Xunit;

namespace HelloCs.Tests
{
    public class HelloControllerTests
    {
        [Fact]
        public void GetHello_ReturnsHelloWorld()
        {
            // Arrange
            var mockService = new Mock<IHelloService>();
            mockService.Setup(s => s.SayHello()).Returns("Hello, World!");
            var controller = new HelloController(mockService.Object);

            // Act
            var result = controller.GetHello();

            // Assert
            var okResult = Assert.IsType<OkObjectResult>(result);
            Assert.Equal("Hello, World!", okResult.Value);
        }
    }
}
