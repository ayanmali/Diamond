import { FC, useMemo } from 'react';
import { ConnectionProvider, WalletProvider, useWallet } from '@solana/wallet-adapter-react';
//import { WalletAdapterNetwork } from '@solana/wallet-adapter-base';
import { PhantomWalletAdapter, CoinbaseWalletAdapter } from '@solana/wallet-adapter-wallets';
import {
    WalletModalProvider,
    WalletDisconnectButton,
    WalletMultiButton
} from '@solana/wallet-adapter-react-ui';
//import { clusterApiUrl } from '@solana/web3.js';
import '@solana/wallet-adapter-react-ui/styles.css'; 
 
const SolWalletConnection: FC = () => {
    // The network can be set to 'devnet', 'testnet', or 'mainnet-beta'.
    // const network = WalletAdapterNetwork.Devnet;
    //const { publicKey } = useWallet();
 
    // You can also provide a custom RPC endpoint.
    // const endpoint = useMemo(() => clusterApiUrl(network), [network]);
    const endpoint = "http://localhost:8899";
 
    const wallets = useMemo(
        () => [
            new PhantomWalletAdapter(),
            new CoinbaseWalletAdapter()
        ],
        [/**network*/]
    );

    const PublicAddress: FC = () => {
        const { publicKey } = useWallet();
        if(publicKey) {
            return <div>Wallet address: {publicKey.toBase58()}</div>;
        } else {
            return <div>Wallet not connected</div>;
        }
    }
 
    return (
        <ConnectionProvider endpoint={endpoint}>
            <WalletProvider wallets={wallets} autoConnect>
                <WalletModalProvider>
                    <WalletMultiButton />
                    <WalletDisconnectButton />
                    { /* Your app's components go here, nested within the context providers. */ }
                    <PublicAddress />            

                </WalletModalProvider>
            </WalletProvider>
        </ConnectionProvider>
    );
};

export default SolWalletConnection;