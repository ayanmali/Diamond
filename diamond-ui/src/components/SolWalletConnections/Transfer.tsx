import {
    Connection,
    clusterApiUrl,
    Keypair,
    LAMPORTS_PER_SOL,
    Transaction,
    sendAndConfirmTransaction,
  } from "@solana/web3.js";

import {
    createMint,
    getOrCreateAssociatedTokenAccount,
    mintTo,
    createTransferInstruction,
  } from "@solana/spl-token";
import { FC } from "react";

async function testTransfer() {
    // Connect to cluster
    const connection = new Connection(clusterApiUrl("devnet"), "confirmed");

    // Generate a new wallet keypair and airdrop SOL
    const fromWallet = Keypair.generate();
    console.log(`From wallet public key: ${fromWallet.publicKey}`);
    console.log(`From wallet secret key: ${fromWallet.secretKey}`);

    const fromAirdropSignature = await connection.requestAirdrop(
        fromWallet.publicKey,
        LAMPORTS_PER_SOL,
    );
    // Wait for airdrop confirmation
    // await connection.confirmTransaction(fromAirdropSignature);

    // Get the hash for the latest block on the blockchain
    const latestBlockHash = await connection.getLatestBlockhash();
    console.log(`Latest block hash: ${latestBlockHash}`);

    console.log("Confirming transaction...");

    // Waiting to receive a confirmation that the airdrop was received
    await connection.confirmTransaction(
        {
        blockhash: latestBlockHash.blockhash,
        lastValidBlockHeight: latestBlockHash.lastValidBlockHeight,
        signature: fromAirdropSignature,
        }
    );
    console.log("Confirmed transaction");

    // Generate a new wallet to receive newly minted token
    const toWallet = Keypair.generate();
    console.log(`To wallet public key: ${toWallet.publicKey}`);
    console.log(`To wallet secret key: ${toWallet.secretKey}`);

    // Create new token mint
    const mint = await createMint(
        connection,
        fromWallet,
        fromWallet.publicKey,
        null,
        9,
    );
    console.log("Minting...");


    // Get the token account of the fromWallet Solana address, if it does not exist, create it
    const fromTokenAccount = await getOrCreateAssociatedTokenAccount(
        connection,
        fromWallet,
        mint,
        fromWallet.publicKey,
    );

    console.log(`fromTokenAccount address: ${fromTokenAccount.address}`);

    //get the token account of the toWallet Solana address, if it does not exist, create it
    const toTokenAccount = await getOrCreateAssociatedTokenAccount(
        connection,
        fromWallet,
        mint,
        toWallet.publicKey,
    );

    console.log(`toTokenAccount address: ${toTokenAccount.address}`);

    // Minting 1 new token to the "fromTokenAccount" account we just returned/created
    await mintTo(
        connection,
        fromWallet,
        mint,
        fromTokenAccount.address,
        fromWallet.publicKey,
        1 * LAMPORTS_PER_SOL, // it's 1 token, but in lamports
        [],
    );

    // Add token transfer instructions to transaction
    const transaction = new Transaction().add(
        createTransferInstruction(
        fromTokenAccount.address,
        toTokenAccount.address,
        fromWallet.publicKey,
        1,
        ),
    );

    // Sign transaction, broadcast, and confirm
    await sendAndConfirmTransaction(connection, transaction, [fromWallet]);
};

export const TestTransferComponent: FC = () => {
    return (
        <button onClick={testTransfer}>
            Send tokens
        </button>
    )

}