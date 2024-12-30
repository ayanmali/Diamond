package main

import (
	"fmt"
	"net/http"

	"github.com/gorilla/mux"
)

// func HomeRouteFunc(w http.ResponseWriter, r *http.Request) {
// 	w.Write([]byte("Welcome"))
// 	w.Header().Add("Content-Type", "application/json")
// }

func main() {
	const url string = "https://www.google.com"
	service := NewTokenSwapperService(url)
	service = NewLoggingService(service)

	// err := service.SwapTokensToSpot(context.TODO(),)
	// if err != nil {
	// 	log.Fatal(err)
	// }

	// res := fmt.Sprintf("Swapped %f %v to %v")

	// fmt.Printf("%+v\n", res)

	// defining the port number
	var port int = 8000
	r := mux.NewRouter()

	r.HandleFunc("/", HomeRouteFunc).Methods(http.MethodGet)

	http.Handle("", r)

	// Logging and error handling
	fmt.Printf("Listening on port %d\n", port)
	err := http.ListenAndServe(fmt.Sprintf(":%d", port), nil)
	if err != nil {
		fmt.Errorf("Error when listening on port %d\n", port)
	}
}
