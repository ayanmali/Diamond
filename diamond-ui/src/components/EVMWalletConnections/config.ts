import { http, createConfig } from 'wagmi'
import { base } from 'wagmi/chains'
import { /*injected,*/ metaMask, walletConnect, coinbaseWallet } from 'wagmi/connectors'

const projectId = import.meta.env.VITE_PROJECT_ID;

export const config = createConfig({
  chains: [base], // Only include Base chain for now
  connectors: [
    //injected(),
    walletConnect({ projectId, showQrModal:true }),
    metaMask({
      dappMetadata: {
        name: "Diamond",
        url: "https://diamond.com",
        //iconUrl: "",
      },
      logging: { developerMode: true, sdk: true } // for testing
    }),
    coinbaseWallet({
      appName: "Diamond",
      preference: 'smartWalletOnly',
    }),
  ],
  transports: {
    //[mainnet.id]: http(),
    [base.id]: http(),
  },
})