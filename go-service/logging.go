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
func (s *LoggingService) SwapStablesToSpot(ctxt context.Context, vendorWallet VendorWallet, initialCurrency Currency, amount float64, targetToken Blockchain) (err error) {
	// middleware executes after the service has executed
	defer func(start time.Time) {
		// Logging to the terminal
		fmt.Printf("amount=%.4f currency=%v target=%v err=%s took=%v", amount, initialCurrency, targetToken, err, time.Since(start))
	}(time.Now())

	// continue by calling the service
	return s.next.SwapStablesToSpot(ctxt, vendorWallet, initialCurrency, amount, targetToken)
}

func (s *LoggingService) SwapStablesToFiat(ctxt context.Context, vendorWallet VendorWallet, currency Currency, amount float64) (err error) {
	// middleware executes after the service has executed
	defer func(start time.Time) {
		// Logging to the terminal
		fmt.Printf("amount=%f currency=%v err=%s took=%v", amount, currency, err, time.Since(start))
	}(time.Now())

	// continue by calling the service
	return s.next.SwapStablesToFiat(ctxt, vendorWallet, currency, amount)
}
