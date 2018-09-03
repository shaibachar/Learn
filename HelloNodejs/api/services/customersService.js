const Customer = require("../models/customer");
const mongoose = require("mongoose");

exports.customer_delete = (req, res, next) => {
  const id = req.params.customerId;
  Customer.remove({
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

exports.customer_patch = (req, res, next) => {
  const id = req.params.customerId;
  const updateOps = {};
  for (const ops of req.body) {
    updateOps[ops.propName] = ops.value;
  }

  Customer.update(
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

exports.customer_get_byId = (req, res, next) => {
  const id = req.params.customerId;
  Customer.findById(id)
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
exports.customers_post = (req, res, next) => {
  console.log(req.file);
  const customer = new Customer({
    _id: new mongoose.Types.ObjectId(),
    firstName: req.body.firstName,
    lastName: req.body.lastName,
    customerImage: req.file.path
  });
  customer
    .save()
    .then(result => {
      console.log(result);
      res.status(201).json({
        message: "Created Product successfully",
        createdCustomer: {
          firstName: result.firstName,
          lastName: result.lastName,
          _id: result._id,
          customerImage: result.customerImage,
          request: {
            type: "GET",
            url: "http://localhost:3000/customers/" + result._id
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

exports.customers_get_all = (req, res, next) => {
  Customer.find()
    .select("firstName lastName _id customerImage")
    .exec()
    .then(docs => {
      const response = {
        count: docs.length,
        products: docs.map(doc => {
          return {
            firstName: doc.firstName,
            lastName: doc.lastName,
            customerImage: doc.customerImage,
            _id: doc._id,
            url: {
              type: "GET",
              description: "customers",
              url: "http://localhost:3000/customers/" + doc._id
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
