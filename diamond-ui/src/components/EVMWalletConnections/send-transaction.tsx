/* Sending spot tokens to another wallet */

//import * as React from 'react'
import { FC } from "react"
import { type BaseError,
              useSendTransaction,
              useWaitForTransactionReceipt } from 'wagmi'
import { parseEther } from 'viem'

 
export const SendTransaction: FC = () => {
  const { data: hash, error, isPending, sendTransaction } = useSendTransaction()

  async function submit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    const formData = new FormData(e.target as HTMLFormElement)
    const to = formData.get('address') as `0x${string}`
    const value = formData.get('value') as string

    sendTransaction({ to, value: parseEther(value) })
  }

  const { isLoading: isConfirming, isSuccess: isConfirmed } =
    useWaitForTransactionReceipt({
      hash,
    })

  return (
    <form onSubmit={submit}>
      <input name="address" placeholder="0x..." required />
      <input name="value" placeholder="Enter amount here" required />
      <button type="submit">{isPending ? "Confirming..." : "Send"}</button>
      {hash && <div>Transaction Hash: {hash}</div>}
      {isConfirming && <div>Waiting for confirmation...</div>}
      {isConfirmed && <div>Transaction Confirmed</div>}

      {/* Error handling */}
      {error && (
        <div>Error: {(error as BaseError).shortMessage || error.message}</div>
      )}
    </form>
  )
}