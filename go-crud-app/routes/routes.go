package routes

import (
	"github.com/gin-gonic/gin"
	"github.com/swaggo/gin-swagger"
	"github.com/swaggo/files"
	"go-crud-app/controllers"
)

func SetupRoutes(r *gin.Engine) {
	r.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerFiles.Handler))

	r.POST("/customers", controllers.CreateCustomer)
	// Add other CRUD routes here for customers and products
}
