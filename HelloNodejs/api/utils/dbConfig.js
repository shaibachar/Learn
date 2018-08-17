const mongoose = require("mongoose");

//DB connection start
mongoose.connect(
  process.env.MONGO_DB_URL,
  { useNewUrlParser: true }
);

console.log("DB_CONNECT:"+process.env.MONGO_DB_URL);

var db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error:"));
db.once("open", function callback() {
  console.log("db connected");
});

module.exports = mongoose;
