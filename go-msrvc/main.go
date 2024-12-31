package main

import (
	"fmt"
	"net/http"
)

func main() {
	const port int = 8000

	// fmt.Println("Welcome to Diamond")
	// var myWallet VendorWallet = *createWallet("0x...", "test_wallet", SOL)

	// vendor := createVendorAccount("Diamond", "ayan@gmail.com")
	// vendor.addVendorWallet(myWallet)

	// fmt.Println(vendor)

	http.Handle("/", http.HandlerFunc(testing))
	fmt.Printf("Listening on port %d\n", port)
	http.ListenAndServe(fmt.Sprintf(":%d", port), nil)
}
