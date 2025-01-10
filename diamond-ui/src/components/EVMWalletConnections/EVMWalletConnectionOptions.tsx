import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { WagmiProvider, useAccount } from 'wagmi'
import { config } from './config'
import { Account } from './account'
import { WalletOptions } from './wallet-options'
import { FC } from 'react'
//import { SendTransaction } from './send-transaction'
import Transfer from './transfer-stablecoins'

const queryClient = new QueryClient()

const ConnectWallet: FC = () => {
  const { isConnected } = useAccount()
  if (isConnected) return <Account />
  return <WalletOptions />
}

const EVMWalletConnection: FC = () => {
  return (
    <WagmiProvider config={config}>
      <QueryClientProvider client={queryClient}> 
        <ConnectWallet />
        {/* <SendTransaction /> */}
        <Transfer />
      </QueryClientProvider> 
    </WagmiProvider>
  )
}

export default EVMWalletConnection;