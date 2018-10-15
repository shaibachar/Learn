const http = require("http");
const https = require("https");
const fs = require("fs");
const config = require("./config");
const app = require("./app");

const http_port = process.env.HTTP_PORT || 3000;
const https_port = process.env.HTTPS_PORT || 3001;

const httpOptions = {
  key: fs.readFileSync("./ssl/server.key"),
  cert: fs.readFileSync("./ssl/server.crt")
};

const http_server = http.createServer(app);
http_server.listen(http_port);

const https_server = https.createServer(httpOptions, app);
https_server.listen(https_port);

console.log(config);

module.exports = http_server;
