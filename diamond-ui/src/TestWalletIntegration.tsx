// import React, { useState, useEffect } from 'react';
// import { Card, CardHeader, CardContent } from '@mui/material';
// import { Button } from '@mui/material';
// import { AlertCircle, Wallet } from 'lucide-react';
// import { Alert } from '@mui/material';
// import { LAMPORTS_PER_SOL } from '@solana/web3.js';


// const WalletConnect = () => {
//   const [wallet, setWallet] = useState(null);
//   const [error, setError] = useState('');
//   const [network, setNetwork] = useState('');

//   const detectProvider = () => {
//     if (window.ethereum) {
//       return {
//         type: 'MetaMask',
//         provider: window.ethereum
//       };
//     } else if (window.solana) {
//       return {
//         type: 'Phantom',
//         provider: window.solana
//       };
//     } else if (window.coinbaseWalletExtension) {
//       return {
//         type: 'Coinbase',
//         provider: window.coinbaseWalletExtension
//       };
//     }
//     return null;
//   };

//   const connectWallet = async () => {
//     try {
//       const provider = detectProvider();
//       if (!provider) {
//         setError('No wallet detected. Please install MetaMask, Phantom, or Coinbase Wallet');
//         return;
//       }

//       setError('');
      
//       let account;
//       switch (provider.type) {
//         case 'MetaMask':
//           const accounts = await provider.provider.request({ 
//             method: 'eth_requestAccounts' 
//           });
//           account = accounts[0];
//           const chainId = await provider.provider.request({ 
//             method: 'eth_chainId' 
//           });
//           setNetwork(`Ethereum Network (Chain ID: ${parseInt(chainId, 16)})`);
//           break;

//         case 'Phantom':
//           const connection = await provider.provider.connect();
//           account = connection.publicKey.toString();
//           setNetwork('Solana Network');
//           break;

//         case 'Coinbase':
//           const cbAccounts = await provider.provider.request({ 
//             method: 'eth_requestAccounts' 
//           });
//           account = cbAccounts[0];
//           const cbChainId = await provider.provider.request({ 
//             method: 'eth_chainId' 
//           });
//           setNetwork(`Ethereum Network (Chain ID: ${parseInt(cbChainId, 16)})`);
//           break;
//       }

//       setWallet({
//         address: account,
//         type: provider.type,
//         provider: provider.provider
//       });

//     } catch (err) {
//       setError(err.message);
//     }
//   };

//   const disconnectWallet = () => {
//     setWallet(null);
//     setNetwork('');
//     setError('');
//   };

//   // Listen for account changes
//   useEffect(() => {
//     if (!wallet) return;

//     const handleAccountsChanged = (accounts) => {
//       if (accounts.length === 0) {
//         disconnectWallet();
//       } else {
//         setWallet(prev => ({
//           ...prev,
//           address: accounts[0]
//         }));
//       }
//     };

//     if (wallet.type === 'MetaMask' || wallet.type === 'Coinbase') {
//       wallet.provider.on('accountsChanged', handleAccountsChanged);
//     }

//     return () => {
//       if (wallet.type === 'MetaMask' || wallet.type === 'Coinbase') {
//         wallet.provider.removeListener('accountsChanged', handleAccountsChanged);
//       }
//     };
//   }, [wallet]);

//   return (
//     <Card className="w-full max-w-md">
//       <CardHeader>
//         <div className="flex items-center space-x-2">
//           <Wallet className="w-6 h-6" />
//           <h2 className="text-2xl font-bold">Connect Wallet</h2>
//         </div>
//       </CardHeader>
//       <CardContent>
//         {error && (
//           <Alert variant="destructive" className="mb-4">
//             <AlertCircle className="h-4 w-4" />
//             <AlertDescription>{error}</AlertDescription>
//           </Alert>
//         )}
        
//         {!wallet ? (
//           <Button 
//             onClick={connectWallet}
//             className="w-full"
//           >
//             Connect Wallet
//           </Button>
//         ) : (
//           <div className="space-y-4">
//             <div className="p-4 bg-gray-100 rounded-lg">
//               <p className="font-medium">Connected to {wallet.type}</p>
//               <p className="text-sm text-gray-600 break-all">
//                 Address: {wallet.address}
//               </p>
//               {network && (
//                 <p className="text-sm text-gray-600">
//                   Network: {network}
//                 </p>
//               )}
//             </div>
//             <Button 
//               onClick={disconnectWallet}
//               variant="outline"
//               className="w-full"
//             >
//               Disconnect
//             </Button>
//           </div>
//         )}
//       </CardContent>
//     </Card>
//   );
// };

// export default WalletConnect;