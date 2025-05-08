using HelloCs.Services;
using Microsoft.AspNetCore.Mvc;

namespace HelloCs.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class HelloController : ControllerBase
    {
        private readonly IHelloService _helloService;

        public HelloController(IHelloService helloService)
        {
            _helloService = helloService;
        }

        [HttpGet]
        public IActionResult GetHello()
        {
            return Ok(_helloService.SayHello());
        }
    }
}
