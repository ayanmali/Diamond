pragma solidity ^0.8.0;

interface IERC20 {
    function transferFrom(address sender, address recipient, uint256 amount) external returns (bool);
}

contract SubscriptionManager {
    struct Subscription {
        address subscriber;
        address recipient;
        uint256 amount;
        uint256 interval;
        uint256 nextPayment;
        bool autoRenew;
        bool active;
    }
    
    mapping(uint256 => Subscription) public subscriptions;
    uint256 public subscriptionCount;
    
    // Events for logging operations.
    event SubscriptionCreated(uint256 indexed id, address subscriber, address recipient, uint256 amount, uint256 interval);
    event PaymentProcessed(uint256 indexed id, uint256 timestamp);
    event SubscriptionCancelled(uint256 indexed id, uint256 timestamp);
    
    /// @notice Create a new subscription.
    function createSubscription(
        address _recipient,
        uint256 _amount,
        uint256 _interval,
        bool _autoRenew
    ) external returns (uint256) {
        subscriptionCount++;
        subscriptions[subscriptionCount] = Subscription({
            subscriber: msg.sender,
            recipient: _recipient,
            amount: _amount,
            interval: _interval,
            nextPayment: block.timestamp + _interval,
            autoRenew: _autoRenew,
            active: true
        });
        emit SubscriptionCreated(subscriptionCount, msg.sender, _recipient, _amount, _interval);
        return subscriptionCount;
    }
    
    /// @notice Cancel an active subscription.
    function cancelSubscription(uint256 _id) external {
        Subscription storage sub = subscriptions[_id];
        require(msg.sender == sub.subscriber, "Only subscriber can cancel");
        require(sub.active, "Subscription already inactive");
        sub.active = false;
        emit SubscriptionCancelled(_id, block.timestamp);
    }
    
    /// @notice Process a due payment.
    /// This function should be called (directly or via automation) when payment is due.
    function processPayment(uint256 _id, address tokenAddress) external {
        Subscription storage sub = subscriptions[_id];
        require(sub.active, "Inactive subscription");
        require(block.timestamp >= sub.nextPayment, "Payment not due yet");
        
        // Pull tokens from the subscriber (requires prior approval).
        bool success = IERC20(tokenAddress).transferFrom(sub.subscriber, sub.recipient, sub.amount);
        require(success, "Token transfer failed");
        
        emit PaymentProcessed(_id, block.timestamp);
        // Set up the next payment, or mark inactive if not auto-renewing.
        if (sub.autoRenew) {
            sub.nextPayment += sub.interval;
        } else {
            sub.active = false;
        }
    }
}
