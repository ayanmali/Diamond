import { initializeConnector } from '@web3-react/core'
import { CoinbaseWallet } from '@web3-react/coinbase-wallet'
import { Connection, ConnectionType, onConnectionError } from './connections.ts'
import { INPUT_CHAIN_URL } from './constants.ts'

export function buildCoinbaseWalletConnector() {
  const [web3CoinbaseWallet, web3CoinbaseWalletHooks] =
    initializeConnector<CoinbaseWallet>(
      (actions) =>
        new CoinbaseWallet({
          actions,
          options: {
            url: INPUT_CHAIN_URL(),
            appName: 'Uniswap',
            reloadOnDisconnect: false,
          },
          onError: onConnectionError,
        })
    )
  const coinbaseWalletConnection: Connection = {
    connector: web3CoinbaseWallet,
    hooks: web3CoinbaseWalletHooks,
    type: ConnectionType.COINBASE_WALLET,
  }

  return coinbaseWalletConnection
}