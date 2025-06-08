package main

import (
	"go-crud-app/config"
	"go-crud-app/routes"
	"github.com/gin-gonic/gin"
	_ "go-crud-app/docs"
)

// @title Go CRUD App API
// @version 1.0
// @description API Server for managing customers and products.
// @host localhost:8080
// @BasePath /

func main() {
	config.ConnectDB()
	r := gin.Default()
	routes.SetupRoutes(r)
	r.Run(":8080")
}
