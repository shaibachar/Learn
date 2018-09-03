const express = require("express");
const router = express.Router();
const reportService = require("../services/reportsService");
const checkAuth = require("../middleware/check-auth");

router.get("/", checkAuth, reportService.reports_get_all);

router.post("/", checkAuth, reportService.report_post);

router.post("/create", checkAuth, reportService.report_create);

router.get("/:reportId", checkAuth, reportService.report_get_byId);

router.patch("/:reportId", checkAuth, reportService.report_patch);

router.delete("/:reportId", checkAuth, reportService.report_delete);

module.exports = router;
