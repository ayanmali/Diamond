/*
 * Microservice allowing users to swap to and from stables and spot tokens seamlessly
 * todo: configure the API requests to a crypto exchange to integrate swapping functionality
 */

package main

import (
	"context"
	"encoding/json"
	"fmt"
	"net/http"
)

// Interface to define the method(s) for a service
type Service interface {
	SwapTokensToSpot(ctxt context.Context, vendorWallet VendorWallet, startCurrency Currency, amount float64, targetCurrency Currency) error // method for swapping stablecoins into spot tokens
	SwapTokensToFiat(ctxt context.Context, vendorWallet VendorWallet, currency Currency, amount float64) error                               // method for swapping stablecoins into fiat currency (USD, CAD, EUR, etc)
}

type TokenSwapperService struct {
	url string // API route for making the swap on the exchange
}

// Constructor for the TokenSwapperService
func NewTokenSwapperService(url string) Service {
	return &TokenSwapperService{url: url}
}

// Implementing the interface
func (s *TokenSwapperService) SwapTokensToSpot(ctxt context.Context, vendorWallet VendorWallet, startCurrency Currency, amount float64, targetCurrency Currency) error {
	// sending API request to the exchange
	fmt.Println("Simulating API request to exchange...")
	return nil
}

func (s *TokenSwapperService) SwapTokensToFiat(ctxt context.Context, vendorWallet VendorWallet, currency Currency, amount float64) error {
	// sending API request to the exchange
	fmt.Println("Simulating API request to exchange...")
	return nil
}

func HomeRouteFunc(w http.ResponseWriter, r *http.Request) {
	test := map[string]string{"result": "Testing Diamond JSON microservice"}
	w.Header().Add("Content-Type", "application/json")
	json.NewEncoder(w).Encode(test)
}
