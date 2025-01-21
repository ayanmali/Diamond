/*
Component to enable payment with spot SOL
*/

import { WalletNotConnectedError } from "@solana/wallet-adapter-base";
import { useConnection, useWallet } from "@solana/wallet-adapter-react";
import { LAMPORTS_PER_SOL, PublicKey, /*sendAndConfirmTransaction,*/ SystemProgram, Transaction } from '@solana/web3.js';
import { ASSOCIATED_TOKEN_PROGRAM_ID, createAssociatedTokenAccountInstruction, createTransferInstruction, TOKEN_PROGRAM_ID } from "@solana/spl-token";

import { FC, useCallback } from "react";
//import { chains } from "../../constants";

export const SendSOL: FC = () => {
    const { publicKey, sendTransaction } = useWallet();
    const { connection } = useConnection();

    const onClick = useCallback(async () => {
        if (!publicKey) throw new WalletNotConnectedError();

        const fee = await connection.getMinimumBalanceForRentExemption(0);
        const amount = (LAMPORTS_PER_SOL * 1.2)

        const toAddressPubKey = new PublicKey("6yE3DNXsgVsMGqhp7GMtPRSRUSqwxhMNVLrVuA1RNFyt");

        console.log(`Sending tokens to ${toAddressPubKey}`);

        const transaction = new Transaction().add(
            SystemProgram.transfer({
                fromPubkey: publicKey, // the user's connected wallet is the sender
                toPubkey: toAddressPubKey,
                lamports: fee + amount,
            })
        );

        console.log("Creating transaction");

        const {
            context: { slot: minContextSlot },
            value: { blockhash, lastValidBlockHeight }
        } = await connection.getLatestBlockhashAndContext();

        const signature = await sendTransaction(transaction, connection, { minContextSlot });
        console.log(`Signature: ${signature}`);

        await connection.confirmTransaction({ blockhash, lastValidBlockHeight, signature });
        console.log("Transaction successful");

    }, [publicKey, sendTransaction, connection]);

    return (
        <button onClick={onClick} disabled={!publicKey}>
            Send SOL
        </button>
    );

}

export const SendToken: FC = () => {
    const { wallet, publicKey, sendTransaction } = useWallet();
    const { connection } = useConnection();
    
    const onClick = useCallback(async () => {
        if (!publicKey) throw new WalletNotConnectedError();

        if (wallet === null) throw new Error("No wallet connected");

        // const fee = await connection.getMinimumBalanceForRentExemption(0);
        const amount = 5;

        // Set the token mint address and recipient's public key
        const tokenAddress = new PublicKey('4zMMC9srt5Ri5X14GAgXhaHii3GnPAEERYPJgZJDncDU'); // Replace with the token mint address
        const toWalletPublicKey = new PublicKey("8JHEWpD8Bc8dxn1provERLx54Ah1cwREJXaQT3ebLe3K"); // Replace with the recipient's public key

        // Get the connected wallet's public key
        const fromWalletPublicKey = publicKey;

        // Create associated token account for the sender if it doesn't exist
        const fromTokenAccountAddress = PublicKey.findProgramAddressSync(
            [
                fromWalletPublicKey.toBuffer(),
                TOKEN_PROGRAM_ID.toBuffer(),
                tokenAddress.toBuffer()
            ], ASSOCIATED_TOKEN_PROGRAM_ID);

        const fromTokenAccount = fromTokenAccountAddress[0];

        // Create associated token account for the recipient if it doesn't exist
        const toTokenAccountAddress = PublicKey.findProgramAddressSync(
            [
                toWalletPublicKey.toBuffer(), 
                TOKEN_PROGRAM_ID.toBuffer(),
                tokenAddress.toBuffer()
            ], TOKEN_PROGRAM_ID);

        const toTokenAccount = toTokenAccountAddress[0];

        const transaction = new Transaction();

        // Create the sender's token account if it doesn't exist
        transaction.add(createAssociatedTokenAccountInstruction(
            fromWalletPublicKey, fromTokenAccount, fromWalletPublicKey, tokenAddress
        ));

        // Create the recipient's token account if it doesn't exist
        transaction.add(createAssociatedTokenAccountInstruction(
            fromWalletPublicKey, toTokenAccount, toWalletPublicKey, tokenAddress
        ));

        transaction.add(createTransferInstruction(
            fromTokenAccount, toTokenAccount, fromWalletPublicKey, amount * LAMPORTS_PER_SOL, [], TOKEN_PROGRAM_ID
        ));
        
        console.log("Creating transaction");

        const {
            context: { slot: minContextSlot },
            value: { blockhash, lastValidBlockHeight }
        } = await connection.getLatestBlockhashAndContext();

        try {
            const signature = await sendTransaction(transaction, connection, { minContextSlot });
            
            console.log("Transaction signature (ID):", signature);

            await connection.confirmTransaction({ blockhash, lastValidBlockHeight, signature });
            console.log("Transaction successful");
        } catch (error) {
             console.error("Transaction failed", error);
             //return { txid: null };
        }

    }, [wallet, publicKey, connection, sendTransaction]);

    return (
        <button onClick={onClick} disabled={!publicKey}>
            Send tokens
        </button>
    );
}