package controllers

import (
	"go-crud-app/config"
	"go-crud-app/models"
	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"context"
	"time"
	"net/http"
)

var customerCollection *mongo.Collection = config.DB.Collection("customers")

// @Summary Create a customer
// @Accept json
// @Produce json
// @Param customer body models.Customer true "Customer"
// @Success 200 {object} models.Customer
// @Router /customers [post]
func CreateCustomer(c *gin.Context) {
	var customer models.Customer
	if err := c.BindJSON(&customer); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	res, err := customerCollection.InsertOne(ctx, customer)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}
	customer.ID = res.InsertedID.(string)
	c.JSON(http.StatusOK, customer)
}
