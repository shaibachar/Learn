package models

type Customer struct {
	ID    string `json:"id" bson:"_id,omitempty"`
	Name  string `json:"name"`
	Email string `json:"email"`
}
