import { ReactNode } from 'react'
import { Web3ReactProvider } from '@web3-react/core'
import { PRIORITIZED_CONNECTORS } from '../connections'
import { useEagerlyConnect } from '../hooks'

export const Web3Provider = ({ children }: { children: ReactNode }) => {
  useEagerlyConnect()

  return (
    <Web3ReactProvider
      connectors={PRIORITIZED_CONNECTORS.map((connector) => [
        connector.connector,
        connector.hooks,
      ])}>
      {children}
    </Web3ReactProvider>
  )
}