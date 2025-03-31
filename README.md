# Diamond - Accept payments with crypto
Diamond is a cryptocurrency payment gateway that allows businesses to accept payment from customers with cryptocurrencies, including BTC, ETH, SOL, and USDC.

TODO
- redis caching
- cors
- auth
- csrf protection
- rabbitmq
- batch writes (redis)
- rate limiting w/ leaky bucket algo (redis?)
- load balancing (aws/gcp)
- microservices?

- smart invoices


<!-- 
### Design
- Postgres for main data storage
- Login system + JWTs for authentication/authorization, oauth
- Distributed system for scalability
- Redis for caching

### Planned Features:
- Add a customer to your database to easily send bills to them
- Email/mobile notifications when a bill is sent and when a payment is sent/received 
- Fraud Detection algorithm based on wallet transaction history, age, etc
- Breakdown of holdings for businesses + analytics
    - failed payments, top customers by spend, # transactions over time, gross sales, etc.
    - a table that shows all transactions -- the date, the customer email, amount, currency, status (Succeeded, failed, cancelled, refunded, disputed), option to export as CSV
- Ability to send simple one-time payments, as well as subscriptions/recurring payments
- Ability to create fundraisers/donations
- Ability to swap between coins across the same chain (i.e. stablecoin to spot token or vice versa)
- Cashing out stablecoins to fiat currency
- Assistance with tax filing

#### Dependencies
- solscan api, cryptoapis, circle API -->