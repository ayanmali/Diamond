// main.tsx
import { Web3ReactProvider, Web3ReactHooks } from '@web3-react/core'
import { Connector } from '@web3-react/types'

import allConnections from '../connectors.ts'

import App from './App.tsx'

import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
// import SolWalletConnection from './components/SolWalletConnection.tsx'

// createRoot(document.getElementById('root')!).render(
//   <StrictMode>
//     <App />
//     <SolWalletConnection />
//   </StrictMode>,
// )

const connections: [Connector, Web3ReactHooks][] = allConnections.map(([connector, hooks]) => [connector, hooks])

createRoot(document.getElementById('root') as HTMLElement).render(
  <Web3ReactProvider connectors={connections}>
    <StrictMode>
      <App />
    </StrictMode>
  </Web3ReactProvider>
)