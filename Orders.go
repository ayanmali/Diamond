package main

import (
	"time"
)

// Defining an enum for various blockchain networks
type Blockchain int

const (
	ETH Blockchain = iota
	SOL
	BASE
	TRON
)

var chainName = map[Blockchain]string{
	ETH:  "ETH",
	SOL:  "SOL",
	BASE: "BASE",
	TRON: "TRON",
}

func (chain Blockchain) String() string {
	return chainName[chain]
}

// Defining an enum for stablecoin currencies
type Currency int

const (
	USDC Currency = iota
	EURC
)

var currencyName = map[Currency]string{
	USDC: "USDC",
	EURC: "EURC",
}

func (currency Currency) String() string {
	return currencyName[currency]
}

// Defining an enum for payment statuses
type PaymentStatus int

const (
	Succeeded PaymentStatus = iota
	Failed
	Pending
	Cancelled
)

var statusName = map[PaymentStatus]string{
	Succeeded: "Succeeded",
	Failed:    "Failed",
	Pending:   "Pending",
	Cancelled: "Cancelled",
}

func (status PaymentStatus) String() string {
	return statusName[status]
}

// Defining a vendor's crypto wallet
type VendorWallet struct {
	Address          string
	WalletId         int64
	WalletName       string
	Chain            Blockchain
	UsdcBalance      float64
	EurcBalance      float64
	SpotTokenBalance float64
	DateCreated      int64
}

type Customer struct {
	Email           string
	WalletAddresses []string
}

func createCustomer(string email, []string walletAddresses) *Customer {
	return &Customer(
		email,
		walletAddresses,
	)
}

func(customer *Customer) EditCustomerEmail(string newEmail) {
	customer.Email = newEmail
}

func(customer *Customer) AddWalletAddress(string address) {
	customer.WalletAddresses = append(customer.WalletAddresses, address)
}

// Defining the struct of a simple one time payment
type SimplePayment struct {
	Amount                float64
	VendorWalletAddress   string
	VendorWalletId        int64
	Currency              Currency
	Customer              Customer
	CustomerWalletAddress string
	Timestamp             int64
	Status                PaymentStatus
	VendorComments        string
	CustomerComments      string
}

// Creates a new wallet for the vendor
func createWallet(address string, walletName string, chain Blockchain) *VendorWallet {
	return &VendorWallet{
		Address:          address,
		WalletId:         0, // replace this with a serial value
		WalletName:       walletName,
		Chain:            chain,
		UsdcBalance:      0,
		EurcBalance:      0,
		SpotTokenBalance: 0,
		DateCreated:      time.Now().UnixNano(),
	}
}
