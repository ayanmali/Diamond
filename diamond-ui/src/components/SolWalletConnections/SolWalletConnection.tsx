import { FC, useMemo } from 'react';
import { ConnectionProvider, WalletProvider, useWallet } from '@solana/wallet-adapter-react';

import { WalletAdapterNetwork } from '@solana/wallet-adapter-base';
import { CoinbaseWalletAdapter, SolflareWalletAdapter, TorusWalletAdapter, LedgerWalletAdapter, TrezorWalletAdapter, WalletConnectWalletAdapter } from '@solana/wallet-adapter-wallets';
import {
     WalletModalProvider,
     WalletDisconnectButton,
     WalletMultiButton
 } from '@solana/wallet-adapter-react-ui';
import { clusterApiUrl } from '@solana/web3.js';
// import { solana, solanaTestnet, solanaDevnet } from '@reown/appkit/networks'
// import { createAppKit, useAppKit } from '@reown/appkit/react'
// import { SolanaAdapter } from '@reown/appkit-adapter-solana/react'
// import getProvider from './getProvider';
import '@solana/wallet-adapter-react-ui/styles.css'; 
// import { TestTransferComponent } from './Transfer';
import { SendSOL } from './SendSol';
import SendSolanaSplTokens from './SendSplTokens';
import { FetchSolBalance, FetchSplTokenBalance } from './GetBalances';
import { chains } from '../../constants';
 
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
            new WalletConnectWalletAdapter({
                network: network,
                options: {
                    projectId: import.meta.env.VITE_PROJECT_ID,
                },
            }),
            new SolflareWalletAdapter(),
            new TorusWalletAdapter(),
            new LedgerWalletAdapter(),
            new TrezorWalletAdapter(),
        ],
        [network]
    );

    const PublicAddress: FC = () => {
        const { publicKey } = useWallet();
        if(publicKey) {
            return <div>Wallet address: {publicKey.toBase58()}</div>;
        } else {
            return <div>Wallet not connected</div>;
        }
    }

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

    // const Idk: FC = () => {
    //     const { wallet, publicKey } = useWallet();
    //     function idk() {
    //         // if (!wallet) return;
    //         // const accounts = publicKey ? wallet.adapter.walletAdapter?.wallet?.accounts : [];
    //         // const walletAddresses = accounts.map((account: { publicKey: string }) => new PublicKey(account.publicKey));
    //         // console.log(walletAddresses);
    //         const win = window.solana;
            
        
    //         // Process the accounts as needed
    //         console.log(accounts);
    //     }

    //     return <button onClick={idk}>Display all wallets</button>
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
                    <FetchSolBalance endpoint={endpoint} />
                    <FetchSplTokenBalance endpoint={endpoint} tokenAddress={chains.SOL.usdcAddress} />
                </WalletModalProvider>
            </WalletProvider>
        </ConnectionProvider>
    );
};

export default SolWalletConnection;