package main

import (
	"context"
	"fmt"
	"time"
)

// Custom middleware type
type LoggingService struct {
	next SwapperService
}

func NewLoggingService(next SwapperService) SwapperService {
	return &LoggingService{next: next}
}

// implementing the interface
func (s *LoggingService) SwapStablesToSpot(ctxt context.Context, AccountWallet AccountWallet, initialStablecoinCurrency StablecoinCurrency, amount float64, targetToken Blockchain) (err error) {
	// middleware executes after the service has been called
	defer func(start time.Time) {
		// Logging to the terminal
		fmt.Printf("amount=%.4f currency=%v target=%v err=%s took=%v", amount, initialStablecoinCurrency, targetToken, err, time.Since(start))
	}(time.Now())

	// continue by calling the service
	return s.next.SwapStablesToSpot(ctxt, AccountWallet, initialStablecoinCurrency, amount, targetToken)
}

func (s *LoggingService) SwapStablesToFiat(ctxt context.Context, AccountWallet AccountWallet, stableCurrency StablecoinCurrency, amount float64) (err error) {
	// middleware executes after the service has been called
	defer func(start time.Time) {
		// Logging to the terminal
		fmt.Printf("amount=%f currency=%v err=%s took=%v", amount, stableCurrency, err, time.Since(start))
	}(time.Now())

	// continue by calling the service
	return s.next.SwapStablesToFiat(ctxt, AccountWallet, stableCurrency, amount)
}

func (s *LoggingService) SwapSpotToFiat(ctxt context.Context, AccountWallet AccountWallet, amount float64) (err error) {
	// middleware executes after the service has been called
	defer func(start time.Time) {
		// Logging to the terminal
		fmt.Printf("amount=%f chain=%v err=%s took=%v", amount, AccountWallet.Chain, err, time.Since(start))
	}(time.Now())

	// continue by calling the service
	return s.next.SwapSpotToFiat(ctxt, AccountWallet, amount)
}
