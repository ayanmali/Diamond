/*
 * Microservice allowing users to swap to and from stables and spot tokens seamlessly
 * todo: configure the API requests to a crypto exchange to integrate swapping functionality
 */

package main

import (
	"context"
	"fmt"
)

// Interface to define the method(s) (business logic) for a service
// Used for the actual business logic + logging middleware
type SwapperService interface {
	SwapStablesToSpot(ctxt context.Context, AccountWallet AccountWallet, initialStablecoinCurrency StablecoinCurrency, amount float64, targetToken Blockchain) error // method for swapping stablecoins into spot tokens
	SwapStablesToFiat(ctxt context.Context, AccountWallet AccountWallet, stableCurrency StablecoinCurrency, amount float64) error                                    // method for swapping stablecoins into fiat currency (USD, CAD, EUR, etc)
	SwapSpotToFiat(ctxt context.Context, AccountWallet AccountWallet, amount float64) error                                                                          // method for swapping spot tokens into fiat currency
}

type swapperService struct {
	url string // API route for making the swap on the exchange
}

// Constructor for the StablecoinSwapperService
func NewSwapperService(url string) SwapperService {
	return &swapperService{url: url}
}

// Implementing the interface
func (s *swapperService) SwapStablesToSpot(ctxt context.Context, AccountWallet AccountWallet, initialStablecoinCurrency StablecoinCurrency, amount float64, targetToken Blockchain) error {
	// sending API request to the exchange
	fmt.Println("Simulating API request to exchange...")
	return nil
}

func (s *swapperService) SwapStablesToFiat(ctxt context.Context, AccountWallet AccountWallet, stableCurrency StablecoinCurrency, amount float64) error {
	// sending API request to the exchange
	fmt.Println("Simulating API request to exchange...")
	return nil
}

func (s *swapperService) SwapSpotToFiat(ctxt context.Context, AccountWallet AccountWallet, amount float64) error {
	// sending API request to the exchange
	fmt.Println("Simulating API request to exchange...")
	return nil
}

// func HomeRouteFunc(w http.ResponseWriter, r *http.Request) {
// 	test := map[string]string{"result": "Testing Diamond JSON microservice"}
// 	w.Header().Add("Content-Type", "application/json")
// 	json.NewEncoder(w).Encode(test)
// }
