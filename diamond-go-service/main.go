package main

import (
	"context"
	"log"
)

// func HomeRouteFunc(w http.ResponseWriter, r *http.Request) {
// 	w.Write([]byte("Welcome"))
// 	w.Header().Add("Content-Type", "application/json")
// }

func main() {
	const url string = "https://www.google.com"
	service := NewStablecoinSwapperService(url)
	service = NewLoggingService(service)

	var myWallet VendorWallet = *NewVendorWallet("0x000", "myWallet", 0, BASE)

	err := service.SwapStablesToSpot(context.TODO(), myWallet, USDC, 1.0, BASE)

	if err != nil {
		log.Fatal(err)
	}

	//fmt.Printf("Swapped %f %v to %v successfully\n")

	// defining the port number
	// var port int = 8000
	// r := mux.NewRouter()

	// r.HandleFunc("/", HomeRouteFunc).Methods(http.MethodGet)

	// http.Handle("", r)

	// Logging and error handling
	// fmt.Printf("Listening on port %d\n", port)
	// err := http.ListenAndServe(fmt.Sprintf(":%d", port), nil)
	// if err != nil {
	// 	fmt.Errorf("Error when listening on port %d\n", port)
	// }
}
