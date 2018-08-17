const express = require("express");
const router = express.Router();
const customerService = require("../services/customersService");
const checkAuth = require("../middleware/check-auth");
const multerUpload = require("../utils/multerConfig");

router.get("/", checkAuth, customerService.customers_get_all);

router.post("/", checkAuth, multerUpload.single("productImage"));

router.get("/:customerId", checkAuth, customerService.customer_get_byId);

router.patch("/:productId", checkAuth, customerService.customer_patch);

router.delete("/:productId", checkAuth, customerService.customer_delete);

module.exports = router;
