package main

import (
	"context"
	"fmt"
	"time"
)

// Custom middleware type
type LoggingService struct {
	next Service
}

func NewLoggingService(next Service) Service {
	return &LoggingService{next: next}
}

// implementing the interface
func (s *LoggingService) SwapTokensToSpot(ctxt context.Context, vendorWallet VendorWallet, startCurrency Currency, amount float64, targetCurrency Currency) (err error) {
	// middleware executes after the service has executed
	defer func(start time.Time) {
		// Logging to the terminal
		fmt.Printf("amount=%f currency=%v target=%v err=%s took=%v", amount, startCurrency, targetCurrency, err, time.Since(start))
	}(time.Now())

	// continue by calling the service
	return s.next.SwapTokensToSpot(ctxt, vendorWallet, startCurrency, amount, targetCurrency)
}

func (s *LoggingService) SwapTokensToFiat(ctxt context.Context, vendorWallet VendorWallet, currency Currency, amount float64) (err error) {
	// middleware executes after the service has executed
	defer func(start time.Time) {
		// Logging to the terminal
		fmt.Printf("amount=%f currency=%v err=%s took=%v", amount, currency, err, time.Since(start))
	}(time.Now())

	// continue by calling the service
	return s.next.SwapTokensToFiat(ctxt, vendorWallet, currency, amount)
}
