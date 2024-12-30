package main

import (
	"fmt"
	"net/http"
)

// func HomeRouteFunc(w http.ResponseWriter, r *http.Request) {
// 	w.Write([]byte("Welcome"))
// 	w.Header().Add("Content-Type", "application/json")
// }

func main() {
	var port int = 8000
	http.HandleFunc("/", HomeRouteFunc)
	fmt.Printf("Listening on port %d\n", port)
	err := http.ListenAndServe(fmt.Sprintf(":%d", port), nil)
	if err != nil {
		fmt.Errorf("Error when listening on port %d\n", port)
	}
}
