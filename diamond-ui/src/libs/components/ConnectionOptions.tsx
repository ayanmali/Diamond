import { Connector } from '@web3-react/types'
//import React from 'react'
import {
  getHasMetaMaskExtensionInstalled,
  ConnectionType,
} from '../connections.ts'
import { METAMASK_URL } from '../constants.ts'
import { MetaMaskOption } from './MetaMaskOption.tsx'
import { CoinbaseOption } from './CoinbaseOption.tsx'
import { WalletConnectOption } from './WalletConnectOption.tsx'

type ConnectOptionsParams = {
  connectionType: ConnectionType | null
  isActive: boolean
  onActivate: (connector: Connector) => Promise<void>
  onDeactivate: (connector: Connector) => Promise<void>
}

export const ConnectionOptions = ({
  connectionType,
  isActive,
  onActivate,
  onDeactivate,
}: ConnectOptionsParams) => {
  function getOptions(isActive: boolean) {
    const hasMetaMaskExtension = getHasMetaMaskExtensionInstalled()

    let metaMaskOption
    if (!hasMetaMaskExtension) {
      metaMaskOption = (
        <a href={METAMASK_URL}>
          <button>Install Metamask</button>
        </a>
      )
    } else {
      metaMaskOption = (
        <MetaMaskOption
          isActive={isActive}
          connectionType={connectionType}
          onActivate={onActivate}
          onDeactivate={onDeactivate}
        />
      )
    }

    const coinbaseWalletOption = (
      <CoinbaseOption
        isActive={isActive}
        connectionType={connectionType}
        onActivate={onActivate}
        onDeactivate={onDeactivate}
      />
    )

    const walletConnectOption = (
      <WalletConnectOption
        isActive={isActive}
        connectionType={connectionType}
        onActivate={onActivate}
        onDeactivate={onDeactivate}
      />
    )

    return (
      <>
        {metaMaskOption}
        {coinbaseWalletOption}
        {walletConnectOption}
      </>
    )
  }

  return <div className="connectors">{getOptions(isActive)}</div>
}