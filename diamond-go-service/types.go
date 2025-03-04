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
type StablecoinCurrency int

const (
	USDC StablecoinCurrency = iota
	EURC
	USDT
	NIL_CURRENCY
)

var currencyNameToString = map[StablecoinCurrency]string{
	USDC: "USDC",
	EURC: "EURC",
	USDT: "USDT",
}

var currencyNameFromString = map[string]StablecoinCurrency{
	"USDC": USDC,
	"EURC": EURC,
	"USDT": USDT,
}

func (currency StablecoinCurrency) String() (string, bool) {
	res, ok := currencyNameToString[currency]
	if !ok {
		return "", false
	}
	return res, true
}

// Returns the stablecoin StablecoinCurrency enum equivalent to the provided string
func getStablecoinFromString(currencyString string) (coin StablecoinCurrency, ok bool) {
	res, ok := currencyNameFromString[currencyString]
	if !ok {
		return NIL_CURRENCY, false
	}
	return res, true
}

/*           Account LOGIC             */

// A Account's crypto wallet
type AccountWallet struct {
	ID               uint64
	Address          string
	PrivateKey       string
	RecoveryPhrase   []string
	SecondaryKey     string
	TertiaryKey      string
	AccountId         uint64 // corresponds to the Account (user) ID in the DB
	WalletName       string
	Chain            Blockchain
	USDCBalance      float64
	EURCBalance      float64
	USDTBalance      float64
	SpotTokenBalance float64
	DateCreated      int64
}

// Constructor for a AccountWallet struct
func NewAccountWallet(address string, walletName string, AccountId uint64, chain Blockchain) *AccountWallet {
	return &AccountWallet{
		ID:             0,
		Address:        address,
		WalletName:     walletName,
		AccountId:       AccountId,
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
func NewAccountWalletFromPb(address string, AccountId uint64, chain Blockchain) *AccountWallet {
	return &AccountWallet{
		Address:  address,
		AccountId: AccountId,
		Chain:    chain,
	}
}

// Qualities associated with a Account
type Account struct {
	Wallets             []AccountWallet
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

// Constructor for Account struct
func NewAccount(businessName string, email string) *Account {
	return &Account{
		ID:           0,
		BusinessName: businessName,
		PrimaryEmail: email,
		Wallets:      make([]AccountWallet, 3),
		// retrieve from DB or API call
		TotalUSDCBalance:    0,
		TotalEURCBalance:    0,
		TotalUSDTBalance:    0,
		TotalSOLBalance:     0,
		TotalBaseETHBalance: 0,
		DateCreated:         time.Now().UnixNano(),
	}
}
