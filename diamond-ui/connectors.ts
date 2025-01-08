import { initializeConnector, Web3ReactHooks } from '@web3-react/core'
import { Connector, Web3ReactStore } from '@web3-react/types'
import { MetaMask } from '@web3-react/metamask'

const metamask = initializeConnector<MetaMask>((actions) => new MetaMask({ actions }))

const connectors: [Connector, Web3ReactHooks, Web3ReactStore][] = [metamask]

export default connectors