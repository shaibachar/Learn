const mongoose = require('mongoose');

const reportSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    update: mongoose.Schema.Types.Date,
    customersCount: { type: Number, required: true},
    ordersCount: { type: Number, required: true},
    productsCount: { type: Number, required: true},
    usersCount: { type: Number, required: true}
});

module.exports = mongoose.model('Report', reportSchema);