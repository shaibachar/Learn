const Report = require("../models/report");
const Customer = require("../models/customer");
const Product = require("../models/product");
const User = require("../models/user");
const mongoose = require("mongoose");


exports.report_create = (req, res, next) => {
    console.log(req.file);
    customersCount = Customer.count({});
    productsCount = Product.count({});
    ordersCount = Order.count({});
    usersCount = User.count({});

    const report = new Report({
      _id: new mongoose.Types.ObjectId(),
      customersCount: customersCount,
      ordersCount: ordersCount,
      productsCount: productsCount,
      usersCount: usersCount
    });
  
    report
      .save()
      .then(result => {
        console.log(result);
        res.status(201).json({
          message: "Created Report successfully",
          createdReport: {
            customersCount: result.customersCount,
            ordersCount: result.ordersCount,
            productsCount: result.productsCount,
            usersCount: result.usersCount,
            _id: result._id,
            request: {
              type: "GET",
              url: "http://localhost:3000/reports/" + result._id
            }
          }
        });
      })
      .catch(err => {
        console.log(err);
        res.status(500).json({
          error: err
        });
      });
};

exports.report_delete = (req, res, next) => {
  const id = req.params.reportId;
  Report.remove({
    _id: id
  })
    .exec()
    .then(result => {
      res.status(200).json(result);
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
};

exports.report_patch = (req, res, next) => {
  const id = req.params.reportId;
  const updateOps = {};
  for (const ops of req.body) {
    updateOps[ops.propName] = ops.value;
  }

  Report.update(
    {
      _id: id
    },
    {
      $set: updateOps
    }
  )
    .exec()
    .then(result => {
      console.log(result);
      res.status(200).json(result);
    })
    .catch(err => {
      console.log(err);
      res.status(500).json(err);
    });
};

exports.report_get_byId = (req, res, next) => {
  const id = req.params.reportId;
  Report.findById(id)
    .exec()
    .then(doc => {
      console.log("Data from DB: " + doc);
      if (doc) {
        res.status(200).json(doc);
      } else {
        res.status(404).json({
          message: "No Valid value"
        });
      }
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
};

exports.report_post = (req, res, next) => {
  console.log(req.file);
  const report = new Report({
    _id: new mongoose.Types.ObjectId(),
    customersCount: req.body.customersCount,
    ordersCount: req.body.ordersCount,
    productsCount: req.body.productsCount,
    usersCount: req.body.usersCount
  });

  report
    .save()
    .then(result => {
      console.log(result);
      res.status(201).json({
        message: "Created Report successfully",
        createdReport: {
          customersCount: result.customersCount,
          ordersCount: result.ordersCount,
          productsCount: result.productsCount,
          usersCount: result.usersCount,
          _id: result._id,
          request: {
            type: "GET",
            url: "http://localhost:3000/reports/" + result._id
          }
        }
      });
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
};

exports.reports_get_all = (req, res, next) => {
  Report.find()
    .select("customersCount ordersCount productsCount usersCount _id")
    .exec()
    .then(docs => {
      const response = {
        count: docs.length,
        reports: docs.map(doc => {
          return {
            customersCount: doc.customersCount,
            ordersCount: doc.ordersCount,
            productsCount: doc.productsCount,
            usersCount: doc.usersCount,
            _id: doc._id,
            request: {
              type: "GET",
              url: "http://localhost:3000/reports/" + doc._id
            }
          };
        })
      };
      res.status(200).json(response);
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
};
