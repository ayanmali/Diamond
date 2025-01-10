//import { useSimulateContract } from 'wagmi'
import erc20Abi from './erc-20-abi.ts'

import { useWriteContract, useReadContract } from 'wagmi';
import { FC, useState } from 'react';
import { base } from 'wagmi/chains'

const Transfer: FC = () => {
  const [needsApproval, setNeedsApproval] = useState(true);
  const { writeContract, isPending: claimIsPending } = useWriteContract();
  
  // Check allowance
  const { data: allowance } = useReadContract({
    abi: erc20Abi,
    address: "0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913",
    functionName: 'allowance',
    args: [
      '0x93af2601478BA76DF860dc5A38369Ed0CdBdCDf4',  // from address
      '0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913', // spender address (the token contract itself)
    ],
    chainId: base.id,
  });

  // Approve function
  const handleApprove = () => {
    writeContract({
      abi: erc20Abi,
      address: "0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913" as `0x${string}`,
      functionName: 'approve',
      args: [
        '0x93af2601478BA76DF860dc5A38369Ed0CdBdCDf4' as `0x${string}`, // spender
        1000000n, // amount to approve
      ],
      chainId: base.id,
    });
    setNeedsApproval(false);
  };

  // Transfer function
  function handlePayClick() {
    writeContract({
      abi: erc20Abi,
      address: "0x833589fCD6eDb6E08f4c7C32D4f71b54bdA02913" as `0x${string}`,
      functionName: 'transferFrom',
      args: [
        '0x93af2601478BA76DF860dc5A38369Ed0CdBdCDf4' as `0x${string}`,
        '0x918523de26333b0c2096d82d4e700d3c6d8bd39c' as `0x${string}`,
        1000000n,
      ],
      chainId: base.id,
    });
  };
  
  return (
    <div>
      <p>{'Token Balance: ' + 'Token balance here'}</p>
      <p>Allowance: {`${allowance}`}</p>
        <button disabled={!needsApproval} onClick={handleApprove}>
          {needsApproval ? 'Approve' : 'Approved'}
        </button>
        <button disabled={claimIsPending} onClick={handlePayClick}>
          {claimIsPending ? 'Complete In Wallet' : 'Transfer Tokens'}
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