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
//import './App.css'

import { Buffer } from 'buffer'
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { InfiniteSlider } from './components/ui/infinite-slider'

// import Example from './components/EVMWalletConnection/Example.tsx'
// import App from './App.tsx'
// import { Web3Provider } from './evm-wallets-utils/components/Web3Provider.tsx'
// if (window.ethereum) {
//   window.ethereum.autoRefreshOnNetworkChange = false
// }

// Node polyfills required by WalletConnect are no longer bundled with webpack
window.Buffer = Buffer

const root = createRoot(document.getElementById('root') as HTMLElement)
root.render(
  <StrictMode>
    {/* <Web3Provider> */}
      {/* <App /> */}
      <InfiniteSlider gap={24} reverse className="w-full h-full bg-white">
      <img
        src="https://motion-primitives.com/apple_music_logo.svg"
        alt="Apple Music logo"
        className="h-[120px] w-auto"
      />
      <img
        src="https://motion-primitives.com/chrome_logo.svg"
        alt="Chrome logo"
        className="h-[120px] w-auto"
      />
      <img
        src="https://motion-primitives.com/strava_logo.svg"
        alt="Strava logo"
        className="h-[120px] w-auto"
      />
      <img
        src="https://motion-primitives.com/nintendo_logo.svg"
        alt="Nintendo logo"
        className="h-[120px] w-auto"
      />
      <img
        src="https://motion-primitives.com/jquery_logo.svg"
        alt="Jquery logo"
        className="h-[120px] w-auto"
      />
      <img
        src="https://motion-primitives.com/prada_logo.svg"
        alt="Prada logo"
        className="h-[120px] w-auto"
      />
    </InfiniteSlider>
    {/* </Web3Provider> */}
  </StrictMode>
)

/* -------------------- */

// import './index.css'

// import { Buffer } from 'buffer'
// import { StrictMode } from 'react'
// import { createRoot } from 'react-dom/client'
// import EVMWalletConnection from './components/EVMWalletConnections/EVMWalletConnectionOptions'
// import SolWalletConnection from './components/SolWalletConnections/SolWalletConnection'

// // if (window.ethereum) {
// //   window.ethereum.autoRefreshOnNetworkChange = false
// // }

// // Node polyfills required by WalletConnect are no longer bundled with webpack
// window.Buffer = Buffer

// const chain: string = "SOL";

// const root = createRoot(document.getElementById('root') as HTMLElement)
// root.render(
//   <StrictMode>
//     {/* Use Solana wallet integrations if this transaction is over Solana, otherwise, use EVM wallet integrations */}
//     {chain === "SOL" ? <SolWalletConnection /> : <EVMWalletConnection />}
//   </StrictMode>
// )