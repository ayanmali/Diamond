import { base, polygon, bsc } from 'wagmi/chains'

export const chains = {
    SOL: {
        usdcAddress: "EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v",
        eurcAddress: "HzwqbKZw8HxMN6bF2yFZNrht3c2iXXzpKcFu7uBEDKtr",
        usdtAddress: "Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB",
        spotToken: "SOL",
        explorerBaseUrl: "https://solscan.io",
        str: "SOL"
    },
    BASE: {
        usdcAddress: "0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913",
        eurcAddress: "0x60a3E35Cc302bFA44Cb288Bc5a4F316Fdb1adb42",
        usdtAddress: "0xfde4C96c8593536E31F229EA8f37b2ADa2699bb2",
        spotToken: "WETH",
        explorerBaseUrl: "https://basescan.org",
        wagmi: base,
        str: "BASE",
    },
    POLYGON: {
        usdcAddress: "0x2791Bca1f2de4661ED88A30C99A7a9449Aa84174",
        // eurcAddress: "",
        usdtAddress: "0xc2132D05D31c914a87C6611C10748AEb04B58e8F",
        spotToken: "POL",
        explorerBaseUrl: "https://polygonscan.com",
        wagmi: polygon,
        str: "POLYGON",
    },
    BSC: {
        usdcAddress: "0x8AC76a51cc950d9822D68b83fE1Ad97B32Cd580d",
        // eurcAddress: "",
        // usdtAddress: "",
        spotToken: "BNB",
        explorerBaseUrl: "https://bscscan.com",
        wagmi: bsc,
        str: "BSC"
    }
}


export const currencies = {
    USDC: {
        "SOL": chains.SOL.usdcAddress,
        "BASE": chains.BASE.usdcAddress,
        "POLYGON": chains.POLYGON.usdcAddress,
        "BSC": chains.BSC.usdcAddress
    },
    EURC: {
        "SOL": chains.SOL.eurcAddress,
        "BASE": chains.BASE.eurcAddress,
    },
    USDT: {
        "SOL": chains.SOL.usdtAddress,
        "BASE": chains.BASE.usdtAddress,
        "POLYGON": chains.POLYGON.usdtAddress,
    }
} as const;

// helper types
export type SupportedCurrency = keyof typeof currencies;
export type SupportedChain = keyof typeof chains;