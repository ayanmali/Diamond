/*
Component to enable payment with stablecoins on Solana (devnet)
*/

//import React from 'react';
import {
  WalletNotConnectedError,
  SignerWalletAdapterProps
} from '@solana/wallet-adapter-base';
import { useConnection, useWallet } from '@solana/wallet-adapter-react';
import {
  createTransferInstruction,
  createAssociatedTokenAccountInstruction,
  getAssociatedTokenAddress,
  getAccount
} from '@solana/spl-token';
import {
  PublicKey,
  Transaction,
  Connection,
  TransactionInstruction
} from '@solana/web3.js';

const configureAndSendCurrentTransaction  = async (
  transaction: Transaction,
  connection: Connection,
  feePayer: PublicKey,
  signTransaction: SignerWalletAdapterProps['signTransaction']
) => {
  const blockHash = await connection.getLatestBlockhash();
  transaction.feePayer = feePayer;
  transaction.recentBlockhash = blockHash.blockhash;

  const signed = await signTransaction(transaction);
  const signature = await connection.sendRawTransaction(signed.serialize());
  await connection.confirmTransaction({
    blockhash: blockHash.blockhash,
    lastValidBlockHeight: blockHash.lastValidBlockHeight,
    signature
  });

  return signature;
};

const SendSolanaSplTokens: React.FC = () => {
  const { connection } = useConnection();
  const { publicKey, signTransaction } = useWallet();

  const handlePayment = async () => {
    try {
      if (!publicKey || !signTransaction) {
        throw new WalletNotConnectedError();
      }

      const mintToken = new PublicKey(
        '4zMMC9srt5Ri5X14GAgXhaHii3GnPAEERYPJgZJDncDU'
      ); // 4zMMC9srt5Ri5X14GAgXhaHii3GnPAEERYPJgZJDncDU is USDC token address on solana devnet

      const recipientAddress = new PublicKey(
        '8JHEWpD8Bc8dxn1provERLx54Ah1cwREJXaQT3ebLe3K'
      );

      const transactionInstructions: TransactionInstruction[] = [];

      const associatedTokenFrom = await getAssociatedTokenAddress(
        mintToken,
        publicKey
      );

      const fromAccount = await getAccount(connection, associatedTokenFrom);
      const associatedTokenTo = await getAssociatedTokenAddress(
        mintToken,
        recipientAddress
      );
      if (!(await connection.getAccountInfo(associatedTokenTo))) {
        transactionInstructions.push(
          createAssociatedTokenAccountInstruction(
            publicKey,
            associatedTokenTo,
            recipientAddress,
            mintToken
          )
        );
      }
      transactionInstructions.push(
        createTransferInstruction(
          fromAccount.address, // source
          associatedTokenTo, // dest
          publicKey,
          1230000 // transfer 1 USDC, USDC on solana devnet has 6 decimal
        )
      );
      const transaction = new Transaction().add(...transactionInstructions);
      const signature = await configureAndSendCurrentTransaction(
        transaction,
        connection,
        publicKey,
        signTransaction
      );
      console.log(`Signature: ${signature}`);
      // signature is transaction address, you can confirm your transaction on 'https://explorer.solana.com/?cluster=devnet'
    } catch (err) {console.error(err);}
  };

  return <button onClick={handlePayment}>Transfer spl token</button>;
};

export default SendSolanaSplTokens;