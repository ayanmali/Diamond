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
type StablecoinCurrency int

const (
	USDC StablecoinCurrency = iota
	EURC
)

var currencyName = map[StablecoinCurrency]string{
	USDC: "USDC",
	EURC: "EURC",
}

func (currency StablecoinCurrency) String() string {
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
	PrivateKey       string
	RecoveryPhrase   []string
	SecondaryKey     string
	TertiaryKey      string
	WalletId         uint64
	WalletName       string
	Chain            Blockchain
	UsdcBalance      float64
	EurcBalance      float64
	SpotTokenBalance float64
	DateCreated      int64
}

type CustomerWallet struct {
	Address          string
	Chain            Blockchain
	UsdcBalance      float64
	EurcBalance      float64
	SpotTokenBalance float64
}

type Vendor struct {
	Wallets             []VendorWallet
	ID                  uint64
	BusinessName        string
	TotalUSDCBalance    float64
	TotalEURCBalance    float64
	TotalSpotSOLBalance float64
	Email               string
	DateCreated         int64
}

func createVendorAccount(businessName string, email string) *Vendor {
	return &Vendor{
		Wallets:             make([]VendorWallet, 0),
		ID:                  0,
		BusinessName:        businessName,
		TotalUSDCBalance:    0,
		TotalEURCBalance:    0,
		TotalSpotSOLBalance: 0,
		Email:               email,
		DateCreated:         time.Now().UnixNano(),
	}
}

// Creates a new wallet for the vendor
func createWallet(address string, walletName string, chain Blockchain) *VendorWallet {
	return &VendorWallet{
		Address:          address,
		PrivateKey:       "private_key",
		RecoveryPhrase:   []string{"recovery", "phrase"},
		WalletId:         0, // replace this with a serial value
		WalletName:       walletName,
		Chain:            chain,
		UsdcBalance:      0,
		EurcBalance:      0,
		SpotTokenBalance: 0,
		DateCreated:      time.Now().UnixNano(),
	}
}

func (vendor *Vendor) addVendorWallet(wallet VendorWallet) {
	vendor.Wallets = append(vendor.Wallets, wallet)
}

/*           CUSTOMER LOGIC             */

type Customer struct {
	ID      uint64
	Name    string
	Email   string
	Wallets []CustomerWallet
}

func createCustomer(name string, email string) *Customer {
	return &Customer{
		ID:      0,
		Name:    name,
		Email:   email,
		Wallets: make([]CustomerWallet, 0),
	}
}

func (customer *Customer) EditCustomerEmail(newEmail string) {
	customer.Email = newEmail
}

func (customer *Customer) EditCustomerName(newName string) {
	customer.Name = newName
}

func (customer *Customer) AddCustomerWallet(wallet CustomerWallet) {
	customer.Wallets = append(customer.Wallets, wallet)
}

// Defining the struct of a simple one time payment
type Invoice struct {
	Amount             float64
	BusinessWallet     VendorWallet
	StablecoinCurrency StablecoinCurrency
	Customer           Customer
	CustomerWallet     CustomerWallet
	Timestamp          int64
	Status             PaymentStatus
	LocationPaid       string
	VendorComments     string
	CustomerComments   string
}

func requestPayment(amount float64, businessWallet VendorWallet, currency StablecoinCurrency, customer Customer, vendorComments string) *Invoice {
	return &Invoice{
		Amount:             amount,
		BusinessWallet:     businessWallet,
		StablecoinCurrency: currency,
		Customer:           customer,
		Timestamp:          time.Now().UnixNano(),
		Status:             Pending,
		VendorComments:     vendorComments,
	}
}

func (payment *Invoice) sendPayment(customer Customer, custWallet CustomerWallet, customerComments string) {
	// todo: add payment logic w/ crypto api
	payment.LocationPaid = "Here" // replace with the location from where the request is coming from
	payment.Customer = customer
	payment.CustomerWallet = custWallet
	payment.CustomerComments = customerComments
}
