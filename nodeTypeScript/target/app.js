"use strict";
const express = require("express");
const app = express();
const morgan = require("morgan");
const bodyParser = require("body-parser");
const dbConfig = require("./dbConfig");
const productRoutes = require("./api/routes/products");
const orderRoutes = require("./api/routes/orders");
if (process.env.NODE_ENV !== "test") {
    app.use(morgan("dev")); //'combined' outputs the Apache style LOGs
}
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
// CORS Handling
app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin , X-Requested-With, Content-Type, Accept, Authorization");
    if (req.method === "OPTIONS") {
        res.header("Access-Control-Allow-Methods", "PUT, POST, PATCH, DELETE, GET");
        return res.status("200").json({});
    }
    next();
});
//Routes
app.get("/", (req, res, next) => res.json({ message: "Welcome !" }));
app.use("/products", productRoutes);
app.use("/orders", orderRoutes);
app.use((req, res, next) => {
    const error = new Error("Not found");
    error.status = 404;
    next(error);
});
app.use((error, req, res, next) => {
    res.status(error.status || 500);
    res.json({
        error: {
            message: error.message
        }
    });
});
module.exports = app;
