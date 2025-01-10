//import { useSimulateContract } from 'wagmi'
import erc20Abi from './erc-20-abi.ts'

// import { useReadContract } from 'wagmi';
import { writeContract, waitForTransactionReceipt } from '@wagmi/core'
import { FC, useState } from 'react';
import { base } from 'wagmi/chains';
import { config } from './config.ts';

const Transfer: FC = () => {
  // Add state variables for transaction status
  const [isApproving, setIsApproving] = useState(false); // while an approval is underway in the wallet provider
  const [isTransferring, setIsTransferring] = useState(false); // while a transfer is underway in the wallet provider
  const [approveTxHash, setApproveTxHash] = useState<string | null>(null); // to keep track of the blockchain hash for a successful approval
  const [transferTxHash, setTransferTxHash] = useState<string | null>(null); // to keep track of the blockchain hash for a successful token transfer
  const [error, setError] = useState<string | null>(null); // when an approval/transfer fails

  // Convert decimal value to big integer with proper BigInt conversion
  const value = 1.01; // the number of tokens to transfer
  const bigIntegerValue = BigInt(Math.floor(value * 1000000)); // converting the token amount into a format usable by the smart contract

  // Processing the transactions
  async function handlePayClick() {
    setError(null);

    // Attempting to complete the payment (approving and then signing the transaction)
    try {
      // First transaction - Approval
      setIsApproving(true);
      // Generating the hash (tx) for this transaction once it goes through successfully
      const confirmHash = await writeContract(config, {
        abi: erc20Abi, // the ABI for USDC token's smart contract
        address: "0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913" as `0x${string}`, // the Base USDC token address
        functionName: 'approve', // calling the approval function of the smart contract
        args: [
          '0x93af2601478BA76DF860dc5A38369Ed0CdBdCDf4' as `0x${string}`, // the wallet that is sending tokens
          bigIntegerValue, // the amount of tokens approved to be sent
        ],
        chainId: base.id,  // specifying the chain on which this transaction is occuring on
      });
      setApproveTxHash(confirmHash); // Setting the hash once the transaction is successful

      // Waiting for a confirmation that the transaction was successful
      await waitForTransactionReceipt(config, {
        hash: confirmHash, // 
        confirmations: 1, // number of blocks
      });

      setIsApproving(false);

      // Second transaction - Transfer
      setIsTransferring(true);
      const transferHash = await writeContract(config, {
        abi: erc20Abi,
        address: "0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913" as `0x${string}`,
        functionName: 'transferFrom',
        args: [
          '0x93af2601478BA76DF860dc5A38369Ed0CdBdCDf4' as `0x${string}`,
          '0x918523de26333b0c2096d82d4e700d3c6d8bd39c' as `0x${string}`,
          bigIntegerValue,
        ],
        chainId: base.id,
      });
      setTransferTxHash(transferHash);

      await waitForTransactionReceipt(config, {
        hash: transferHash,
        confirmations: 1,
      });
      setIsTransferring(false);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Transaction failed');
      setIsApproving(false);
      setIsTransferring(false);
    }
  };

  // // Check allowance
  // const { data: allowance } = useReadContract({
  //   abi: erc20Abi,
  //   address: "0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913",
  //   functionName: 'allowance',
  //   args: [
  //     '0x93af2601478BA76DF860dc5A38369Ed0CdBdCDf4',  // from address
  //     '0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913', // spender address (the token contract itself)
  //   ],
  //   chainId: base.id,
  // });
  
  return (
    <div>
      {/* User should select what token they want to pay with */}
      <p>{'Spot Token Balance: ' + 'Token balance here'}</p>
      <p>{'USDC Balance: ' + 'Token balance here'}</p>
      <p>{'EURC Balance: ' + 'Token balance here'}</p>
      <p>{'USDT Balance: ' + 'Token balance here'}</p>
      {/* <p>Allowance: {`${allowance}`}</p> */}

      {error && (
        <p style={{ color: 'red' }}>{error}</p>
      )}
      
      {/* Displaying the approval transaction hash */}
      {approveTxHash && (
        <p>
          Approval Tx Hash:{' '}
          <a 
            href={`https://basescan.org/tx/${approveTxHash}`} // Generating a URL to view the transaction on the blockchain explorer
            target="_blank" 
            rel="noopener noreferrer"
          >
            View on BaseScan
          </a>
        </p>
      )}

      {/* Displaying the transfer transaction hash */}
      {transferTxHash && (
        <p>
          Transfer Tx Hash:{' '}
          <a 
            href={`https://basescan.org/tx/${transferTxHash}`} // Generating a URL to view the transaction on the blockchain explorer
            target="_blank" 
            rel="noopener noreferrer"
          >
            View on BaseScan
          </a>
        </p>
      )}

      {/* Button for completing the payment */}
      <button 
        onClick={handlePayClick} // specifying the click handler function
        disabled={isApproving || isTransferring} // only clickable before or after an approval/transfer
      >
        {isApproving ? 'Approving...' : 
         isTransferring ? 'Transferring...' : 
         'Complete In Wallet'}
      </button>
    </div>
  );
}

// const StablecoinTransfer: FC = () => {
//   const { write } = useWriteContract(config);
//   const sendToken = async () => {
//     if (write) {
//       const tx = await write();
//       console.log('Transaction sent:', tx);
//       // Handle transaction confirmation and error handling here
//     }
//   };
// }

export default Transfer;