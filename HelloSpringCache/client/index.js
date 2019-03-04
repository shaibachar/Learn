const request = require("request");
var express = require("express");
var app = express();
app.listen(3000, () => {
  console.log("Server running on port 3000");
});

app.get("/createHebClient", (req, res, next) => {
    var id = encodeURI("id2");
    var firstName = encodeURI("1שלום");
    var lastName = encodeURI("שלום1");
    var phone = encodeURI("111111222");
    var url = "http://springcache:8080/client/createClient?id="+id+"&firstName="+firstName+"&lastName="+lastName+"&phone="+phone
    console.log(url);
    request.post(
        url,
    {
      json: {
       
      }
    },
    (error, res, body) => {
      if (error) {
        console.error(error);
        return;
      }
      console.log(`statusCode: ${res.statusCode}`);
      console.log(body);
    }
  );
});
