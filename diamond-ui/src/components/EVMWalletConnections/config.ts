import { http, createConfig } from 'wagmi'
import { baseSepolia } from 'wagmi/chains'
import { injected, metaMask, safe, walletConnect, coinbaseWallet } from 'wagmi/connectors'

const projectId = import.meta.env.VITE_PROJECT_ID;

export const config = createConfig({
  chains: [baseSepolia], // Only include Base chain for now
  connectors: [
    injected(),
    walletConnect({ projectId, showQrModal:true }),
    metaMask(),
    coinbaseWallet({
      appName: "Diamond",
      preference: 'smartWalletOnly',
    }),
    safe(),
  ],
  transports: {
    //[mainnet.id]: http(),
    [baseSepolia.id]: http(),
  },
})