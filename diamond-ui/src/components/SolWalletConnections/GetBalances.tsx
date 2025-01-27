/*
* Might ignore this for now
*/

import { getAssociatedTokenAddress, getAccount, getOrCreateAssociatedTokenAccount } from "@solana/spl-token";
import { useWallet } from "@solana/wallet-adapter-react";
import { Connection, LAMPORTS_PER_SOL, PublicKey } from '@solana/web3.js';
import { FC, useEffect, useMemo, useState } from "react";

/*
* Retrieves the spot SOL balance for the connected wallet
*/
export const FetchSolBalance: FC<{ endpoint: string }> = ({ endpoint }) => {
    const { publicKey } = useWallet();
    const [balance, setBalance] = useState<number | null>(null);

    const connection = useMemo(() => new Connection(endpoint), [endpoint]);

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
            {publicKey ? `SOL Balance: ${balance}` : ''}
        </div>
    );
};

/*
* Retrieves the balance of any SPL token (USDC, EURC, etc) for the connected wallet
*/
export const FetchSplTokenBalance: FC<{ endpoint: string, tokenAddress: string }> = ({ endpoint, tokenAddress }) => {
    const { publicKey } = useWallet();
    const [balance, setBalance] = useState<number | null>(null);

    const connection = useMemo(() => new Connection(endpoint), [endpoint]);
    const tokenMint = new PublicKey(tokenAddress);

    useEffect(() =>{
        async function getBalance() {
            if (!publicKey) return;

            try {
                //const ata = await getAssociatedTokenAddress(tokenMint, publicKey);
                const ata = getOrCreateAssociatedTokenAccount(connection, publicKey, tokenMint, publicKey)
                const associatedToken = await getAssociatedTokenAddress(
                    tokenMint,
                    publicKey
                );
                const account = await getAccount(connection, associatedToken, commitment, programId);
                setBalance(Number(account.amount) / Math.pow(10, 6)); // USDC has 6 decimal places
              } catch (error) {
                console.error('Error fetching SPL token balance:', error);
                setBalance(null);
              }
        }
        getBalance();
    });

    return (
        <div>
            {publicKey ? `Token Balance: ${balance}` : ''}
        </div>
    )
}