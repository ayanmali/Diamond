// main.tsx
// import { Web3ReactProvider, Web3ReactHooks } from '@web3-react/core'
// import { Connector } from '@web3-react/types'

// import allConnections from '../old-connection/connectors.ts'

// import App from './App.tsx'

// import { StrictMode } from 'react'
// import { createRoot } from 'react-dom/client'
// import './index.css'
// import SolWalletConnection from './components/SolWalletConnection.tsx'

// createRoot(document.getElementById('root')!).render(
//   <StrictMode>
//     <App />
//     <SolWalletConnection />
//   </StrictMode>,
// )

// const connections: [Connector, Web3ReactHooks][] = allConnections.map(([connector, hooks]) => [connector, hooks])

// createRoot(document.getElementById('root') as HTMLElement).render(
//   <Web3ReactProvider connectors={connections}>
//     <StrictMode>
//       <App />
//       {/* <SolWalletConnection /> */}
//     </StrictMode>
//   </Web3ReactProvider>
// )


/* -------------- */

import './index.css'

import { Buffer } from 'buffer'
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import Example from './example/Example.tsx'
import { Web3Provider } from './libs/components/Web3Provider.tsx'
// if (window.ethereum) {
//   window.ethereum.autoRefreshOnNetworkChange = false
// }

// Node polyfills required by WalletConnect are no longer bundled with webpack
window.Buffer = Buffer

const root = createRoot(document.getElementById('root') as HTMLElement)
root.render(
  <StrictMode>
    <Web3Provider>
      <Example />
    </Web3Provider>
  </StrictMode>
)