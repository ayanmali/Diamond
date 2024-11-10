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

/*           VENDOR LOGIC             */

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

type Vendor struct {
	Wallets []VendorWallet
	ID int64
	BusinessName string
	TotalUSDCBalance float64
	TotalEURCBalance float64
	TotalSpotSOLBalance float64
	Email string
	DateCreated int64
}

func createVendorAccount(businessName string, email string) *Vendor {
	return &Vendor(
		Wallets: make([]VendorWallet),
		ID: 0,
		BusinessName: businessName,
		TotalUSDCBalance: 0,
		TotalEURCBalance: 0,
		TotalSpotSOLBalance: 0,
		Email: email
		DateCreated: time.Now().UnixNano(),
	)
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

func(vendor *Vendor) addVendorWallet(wallet VendorWallet) {
	vendor.Wallets = append(vendor.Wallets, wallet)
}

/*           CUSTOMER LOGIC             */

type Customer struct {
	ID              int64
	Email           string
	WalletAddresses []string
}

func createCustomer(string email, []string walletAddresses) *Customer {
	return &Customer(
		ID: 0,
		Email: email,
		WalletAddresses: walletAddresses,
	)
}

func(customer *Customer) EditCustomerEmail(string newEmail) {
	customer.Email = newEmail
}

func(customer *Customer) AddWalletAddress(string walletAddress) {
	customer.WalletAddresses = append(customer.WalletAddresses, walletAddress)
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
