package main

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

/*           VENDOR LOGIC             */

// Defining a vendor's crypto wallet
type VendorWallet struct {
	Address        string
	PrivateKey     string
	RecoveryPhrase []string
	SecondaryKey   string
	TertiaryKey    string
	WalletId       uint64
	WalletName     string
	Chain          Blockchain
	UsdcBalance    float64
	EurcBalance    float64
	SolBalance     float64
	BaseEthBalance float64
	DateCreated    int64
}

type Vendor struct {
	Wallets             []VendorWallet
	ID                  uint64
	BusinessName        string
	TotalUSDCBalance    float64
	TotalEURCBalance    float64
	TotalSpotSolBalance float64
	TotalBaseEthBalance float64
	PrimaryEmail        string
	DateCreated         int64
}
