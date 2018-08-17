const express = require("express");
const router = express.Router();
const ordersService = require("../services/ordersService");
const checkAuth = require("../middleware/check-auth");

router.get("/", checkAuth, ordersService.orders_get_all);

router.post("/", checkAuth, ordersService.orders_create);

router.get("/:orderId", checkAuth, ordersService.order_get);

router.patch("/:orderId", checkAuth, ordersService.order_patch);

router.delete("/:orderId", checkAuth, ordersService.order_delete);

module.exports = router;
