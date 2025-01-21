import { FC, useMemo, useState, useEffect } from 'react';
import { ConnectionProvider, WalletProvider, useWallet } from '@solana/wallet-adapter-react';

import { WalletAdapterNetwork } from '@solana/wallet-adapter-base';
import { CoinbaseWalletAdapter, SolflareWalletAdapter, TorusWalletAdapter, LedgerWalletAdapter, TrezorWalletAdapter } from '@solana/wallet-adapter-wallets';
import {
     WalletModalProvider,
     WalletDisconnectButton,
     WalletMultiButton
 } from '@solana/wallet-adapter-react-ui';
import { clusterApiUrl, Connection, LAMPORTS_PER_SOL, PublicKey } from '@solana/web3.js';
// import getProvider from './getProvider';
import '@solana/wallet-adapter-react-ui/styles.css'; 
// import { TestTransferComponent } from './Transfer';
import { SendSOL } from './SendSol';
import SendSolanaSplTokens from './SendSplTokens';
// import { WalletAdapterNetwork } from '@solana/wallet-adapter-base';
 
const SolWalletConnection: FC = () => {
    // const { publicKey } = useWallet();
    // The network can be set to 'devnet', 'testnet', or 'mainnet-beta'.
    const network = WalletAdapterNetwork.Devnet;
 
    // You can also provide a custom RPC endpoint.
    const endpoint = useMemo(() => clusterApiUrl(network), [network]);
    // const endpoint = "http://localhost:8899";
 
    const wallets = useMemo(
        () => [
            //new PhantomWalletAdapter(),
            new CoinbaseWalletAdapter(),
            new SolflareWalletAdapter(),
            new TorusWalletAdapter(),
            new LedgerWalletAdapter(),
            new TrezorWalletAdapter()
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

    const FetchBalance: FC = () => {
        const { publicKey } = useWallet();
        const [balance, setBalance] = useState<number | null>(null);
        const connection = useMemo(() => new Connection(endpoint), []);

        useEffect(() => {
            async function fetchBalance() {
                if (publicKey) {
                    const balance = await connection.getBalance(new PublicKey(publicKey)) / LAMPORTS_PER_SOL;
                    setBalance(balance);
                }
            };
            fetchBalance();
        }, [publicKey, connection]);

        return (
            <div>
                {publicKey ? `SOL Balance: ${balance}` : 'Wallet not connected'}
            </div>
        );
    };

    // const Connection: FC = () => {
    //     const handleConnect = async () => {
    //         const provider = getProvider();
    //         try {
    //             const resp = await provider?.connect();
    //             if (resp) {
    //                 return <div>{resp.publicKey.toString()}</div>;
    //             }
    //             return <div>No wallet found</div>;
    //         } catch (err) {
    //             console.error(err);
    //             return <div></div>
    //         }
    //     }

    //     return handleConnect();
    // }
 
    return (
        <ConnectionProvider endpoint={endpoint}>
            <WalletProvider wallets={wallets} autoConnect>
                <WalletModalProvider>
                    <WalletMultiButton />
                    <WalletDisconnectButton />
                    { /* Your app's components go here, nested within the context providers. */ }
                    <PublicAddress />
                    {/* <TestTransferComponent /> */}
                    <SendSOL />
                    <SendSolanaSplTokens />
                    <FetchBalance />
                </WalletModalProvider>
            </WalletProvider>
        </ConnectionProvider>
    );
};

export default SolWalletConnection;