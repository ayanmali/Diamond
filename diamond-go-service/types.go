package main

import "time"

// Eenum for various blockchain networks
type Blockchain int

const (
	SOL Blockchain = iota
	BASE
	BSC
	TON
	TRON
	NIL_CHAIN
)

var chainNameToString = map[Blockchain]string{
	SOL:  "SOL",
	BASE: "BASE",
	BSC:  "BSC",
	TON:  "TON",
	TRON: "TRON",
}

var chainNameFromString = map[string]Blockchain{
	"SOL":  SOL,
	"BASE": BASE,
	"BSC":  BSC,
	"TON":  TON,
	"TRON": TRON,
}

func (chain Blockchain) String() (chainName string, ok bool) {
	res, ok := chainNameToString[chain]
	if !ok {
		return "", false
	}
	return res, true
}

// Returns the Blockchain enum equivalent to the provided string
func getChainFromString(chainString string) (chain Blockchain, ok bool) {
	res, ok := chainNameFromString[chainString]
	if !ok {
		return NIL_CHAIN, false
	}
	return res, true
}

// Enum for stablecoin currencies
type Currency int

const (
	USDC Currency = iota
	EURC
	USDT
	NIL_CURRENCY
)

var currencyNameToString = map[Currency]string{
	USDC: "USDC",
	EURC: "EURC",
	USDT: "USDT",
}

var currencyNameFromString = map[string]Currency{
	"USDC": USDC,
	"EURC": EURC,
	"USDT": USDT,
}

func (currency Currency) String() (string, bool) {
	res, ok := currencyNameToString[currency]
	if !ok {
		return "", false
	}
	return res, true
}

// Returns the stablecoin Currency enum equivalent to the provided string
func getStablecoinFromString(currencyString string) (coin Currency, ok bool) {
	res, ok := currencyNameFromString[currencyString]
	if !ok {
		return NIL_CURRENCY, false
	}
	return res, true
}

/*           VENDOR LOGIC             */

// A vendor's crypto wallet
type VendorWallet struct {
	ID               uint64
	Address          string
	PrivateKey       string
	RecoveryPhrase   []string
	SecondaryKey     string
	TertiaryKey      string
	VendorId         uint64 // corresponds to the vendor (user) ID in the DB
	WalletName       string
	Chain            Blockchain
	USDCBalance      float64
	EURCBalance      float64
	USDTBalance      float64
	SpotTokenBalance float64
	DateCreated      int64
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
		USDCBalance:      0,
		EURCBalance:      0,
		USDTBalance:      0,
		SpotTokenBalance: 0,
		DateCreated:      time.Now().UnixNano(),
	}
}

// Used for extracting data sent via a gRPC request
func NewVendorWalletFromPb(address string, vendorId uint64, chain Blockchain) *VendorWallet {
	return &VendorWallet{
		Address:  address,
		VendorId: vendorId,
		Chain:    chain,
	}
}

// Qualities associated with a vendor
type Vendor struct {
	Wallets             []VendorWallet
	ID                  uint64
	BusinessName        string
	TotalUSDCBalance    float64
	TotalUSDTBalance    float64
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
		TotalUSDTBalance:    0,
		TotalSOLBalance:     0,
		TotalBaseETHBalance: 0,
		DateCreated:         time.Now().UnixNano(),
	}
}
