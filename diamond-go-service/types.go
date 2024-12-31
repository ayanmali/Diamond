package main

import "time"

// Eenum for various blockchain networks
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

// Enum for stablecoin currencies
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

/*           VENDOR LOGIC             */

// A vendor's crypto wallet
type VendorWallet struct {
	ID             uint64
	Address        string
	PrivateKey     string
	RecoveryPhrase []string
	SecondaryKey   string
	TertiaryKey    string
	VendorId       uint64 // corresponds to the vendor (user) ID in the DB
	WalletName     string
	Chain          Blockchain
	USDCBalance    float64
	EURCBalance    float64
	SOLBalance     float64
	BaseETHBalance float64
	DateCreated    int64
}

// Constructor for a VendorWallet struct
func NewVendorWallet(address string, walletName string, vendorId uint64, chain Blockchain) *VendorWallet {
	return &VendorWallet{
		ID:             0,
		Address:        address,
		WalletName:     walletName,
		VendorId:       vendorId,
		Chain:          chain,
		PrivateKey:     "private_key",
		RecoveryPhrase: make([]string, 12),
		// retrieve from DB or API call
		USDCBalance:    0,
		EURCBalance:    0,
		SOLBalance:     0,
		BaseETHBalance: 0,
		DateCreated:    time.Now().UnixNano(),
	}
}

// Qualities associated with a vendor
type Vendor struct {
	Wallets             []VendorWallet
	ID                  uint64
	BusinessName        string
	TotalUSDCBalance    float64
	TotalEURCBalance    float64
	TotalSOLBalance     float64
	TotalBaseETHBalance float64
	PrimaryEmail        string
	DateCreated         int64
}

// Constructor for Vendor struct
func NewVendor(businessName string, email string) *Vendor {
	return &Vendor{
		ID:           0,
		BusinessName: businessName,
		PrimaryEmail: email,
		Wallets:      make([]VendorWallet, 3),
		// retrieve from DB or API call
		TotalUSDCBalance:    0,
		TotalEURCBalance:    0,
		TotalSOLBalance:     0,
		TotalBaseETHBalance: 0,
		DateCreated:         time.Now().UnixNano(),
	}
}
