"use strict";
const http = require("http");
if (process.env.NODE_ENV !== "prod") {
    require("dotenv").load();
}
const app = require("./app");
const port = process.env.PORT || 3000;
const server = http.createServer(app);
server.listen(port);
module.exports = server;
