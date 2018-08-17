const express = require("express");
const router = express.Router();
const User = require("../models/user");
const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const checkAuth = require("../middleware/check-auth");

router.get("/", checkAuth, (req, res, next) => {
  User.find()
    .select("email _id")
    .exec()
    .then(docs => {
      const response = {
        count: docs.length,
        user: docs.map(doc => {
          return {
            email: doc.email,
            _id: doc._id
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
});

router.post("/login", (req, res, next) => {
  User.findOne({ email: req.body.email })
    .exec()
    .then(user => {
      if (user) {
        bcrypt.compare(req.body.password, user.password, function(err, result) {
          if (result === false) {
            console.log("error message:" + err);
            return res.status(401).json({
              message: "Login error"
            });
          } else {
            const token = jwt.sign(
              {
                email: user.email,
                userId: user._id
              },
              process.env.JWT_KEY,
              {
                expiresIn: "1h"
              }
            );
            return res.status(200).json({
              message: "Successful login",
              token: token
            });
          }
        });
      } else {
        return res.status(401).json({
          message: "Login error"
        });
      }
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
});

router.post("/", checkAuth, (req, res, next) => {
  User.find({ email: req.body.email })
    .exec()
    .then(user => {
      if (user.length > 0) {
        res.status(409).json({
          message: "email already exists"
        });
      } else {
        bcrypt.hash(req.body.password, 10, (err, hash) => {
          if (err) {
            return res.status(500).json({
              error: err
            });
          } else {
            const user = new User({
              _id: new mongoose.Types.ObjectId(),
              email: req.body.email,
              password: hash
            });
            user
              .save()
              .then(result => {
                console.log(result);
                res.status(201).json({
                  message: "Created User successfully",
                  createdUser: {
                    email: result.email,
                    _id: result._id
                  }
                });
              })
              .catch(err => {
                console.log(err);
                res.status(500).json({
                  error: err
                });
              });
          }
        });
      }
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
});

router.get("/:userId", checkAuth, (req, res, next) => {
  const id = req.params._id;
  User.findById(id)
    .select("email _id")
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
});

router.patch("/:userId", checkAuth, (req, res, next) => {
  const id = req.params._id;
  const updateOps = {};
  for (const ops of req.body) {
    updateOps[ops.propName] = ops.value;
  }

  User.update(
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
});

router.delete("/:userId", checkAuth, (req, res, next) => {
  const id = req.params.userId;
  User.remove({
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
});

module.exports = router;
