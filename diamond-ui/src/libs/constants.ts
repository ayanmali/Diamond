import { CurrentConfig, Chain } from '../config'

// Chains
const MAINNET_CHAIN_ID = 1
const POLYGON_CHAIN_ID = 137
const BASE_CHAIN_ID = 8453

export const INPUT_CHAIN_ID = () => {
  switch(CurrentConfig.chain) {
    case Chain.POLYGON:
      return POLYGON_CHAIN_ID;
    case Chain.BASE:
      return BASE_CHAIN_ID;
    case Chain.MAINNET:
      return MAINNET_CHAIN_ID
  }
}

export const INPUT_CHAIN_URL = () => {
  switch(CurrentConfig.chain) {
    case Chain.POLYGON:
      return CurrentConfig.rpc.polygon;
    case Chain.BASE:
      return CurrentConfig.rpc.base;
    case Chain.MAINNET:
      return CurrentConfig.rpc.mainnet;
  }
}

export const CHAIN_TO_URL_MAP = {
  [POLYGON_CHAIN_ID]: CurrentConfig.rpc.polygon,
  [BASE_CHAIN_ID]: CurrentConfig.rpc.base,
  [MAINNET_CHAIN_ID]: CurrentConfig.rpc.mainnet,
}

type ChainInfo = {
  explorer: string
  label: string
  nativeCurrency: {
    name: string
    symbol: string
    decimals: 18
  }
  rpcUrl: string
}

export const CHAIN_INFO: { [key: string]: ChainInfo } = {
  [MAINNET_CHAIN_ID]: {
    explorer: 'https://etherscan.io/',
    label: 'Ethereum',
    nativeCurrency: { name: 'Ether', symbol: 'ETH', decimals: 18 },
    rpcUrl: CurrentConfig.rpc.mainnet,
  },
  [POLYGON_CHAIN_ID]: {
    explorer: 'https://polygonscan.com/',
    label: 'Polygon',
    nativeCurrency: { name: 'Polygon Matic', symbol: 'MATIC', decimals: 18 },
    rpcUrl: CurrentConfig.rpc.polygon,
  },
  [BASE_CHAIN_ID]: {
    explorer: 'https://basescan.com/',
    label: 'Base',
    nativeCurrency: { name: 'Wrapped Ether', symbol: 'WETH', decimals: 18 },
    rpcUrl: CurrentConfig.rpc.base,
  },
}

// URLs
export const METAMASK_URL = 'https://metamask.io/'