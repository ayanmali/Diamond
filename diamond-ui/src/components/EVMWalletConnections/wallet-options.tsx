//import * as React from 'react'
import { useConnect } from 'wagmi'

export const WalletOptions = () => {
  const { connectors, connect, isPending } = useConnect()

  return (
    <>
      {connectors.map((connector) => (
        <button key={connector.uid} onClick={() => connect({ connector })}>
          {connector.name}
        </button>
      ))}
      {isPending && <p>Connecting...</p>} {/* Displaying a message  */}
    </>
  )
}