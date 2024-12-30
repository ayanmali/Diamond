package main

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
	Id             uint64
	Address        string
	PrivateKey     string
	RecoveryPhrase []string
	SecondaryKey   string
	TertiaryKey    string
	VendorId       uint64 // corresponds to the vendor (user) ID in the DB
	WalletName     string
	Chain          Blockchain
	UsdcBalance    float64
	EurcBalance    float64
	SolBalance     float64
	BaseEthBalance float64
	DateCreated    int64
}

// Qualities associated with a vendor
type Vendor struct {
	Wallets             []VendorWallet
	ID                  uint64
	BusinessName        string
	TotalUSDCBalance    float64
	TotalEURCBalance    float64
	TotalSolBalance     float64
	TotalBaseEthBalance float64
	PrimaryEmail        string
	DateCreated         int64
}
