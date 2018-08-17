const mongoose = require('mongoose');

const customerSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    firstName: { type: String, required: true},
    lastName: { type: String, required: true},
    customerImage: {type: String, required: false}
});

module.exports = mongoose.model('Customer', customerSchema);