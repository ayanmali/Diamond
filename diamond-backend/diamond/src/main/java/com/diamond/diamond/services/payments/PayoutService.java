package com.diamond.diamond.services.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.payouts.FetchPayoutDto;
import com.diamond.diamond.dtos.payouts.NewPayoutDto;
import com.diamond.diamond.entities.payments.Payout;
import com.diamond.diamond.repositories.payments.PayoutRepository;
import com.diamond.diamond.types.PayoutStatus;

@Service
public class PayoutService {
    private final PayoutRepository payoutRepository;

    public PayoutService(PayoutRepository payoutRepository) {
        this.payoutRepository = payoutRepository;
    }

    // public FetchPayoutDto convertPayoutToFetchDto(Payout payout) {
    //     FetchPayoutDto payoutDto = new FetchPayoutDto();

    //     payoutDto.setId(payout.getId());
    //     payoutDto.setAccountId(payout.getAccount().getId());
    //     payoutDto.setAmount(payout.getAmount());
    //     payoutDto.setFiatCurrency(payout.getFiatCurrency());
    //     payoutDto.setPayoutDate(payout.getPayoutDate());
    //     payoutDto.setStablecoinCurrency(payout.getStablecoinCurrency());
    //     payoutDto.setWalletId(payout.getOfframpWallet().getId());
    //     payoutDto.setWalletAddress(payout.getOfframpWallet().getAddress());
    //     payoutDto.setStatus(payout.getStatus());

    //     return payoutDto;
    // }

    public FetchPayoutDto createPayout(NewPayoutDto input, UUID accountId) {
        // TODO: payout logic with Banxa API
        // ...

        // Creating the Payout object
        Payout payout = new Payout(accountId, input.getWalletId(), input.getAmount(), input.getStablecoinCurrency(), input.getFiatCurrency());

        return new FetchPayoutDto(payoutRepository.save(payout));
    }

    public List<FetchPayoutDto> findPayoutDtosWithFilters(
        UUID id,
        UUID accountId,
        UUID walletId,
        BigDecimal amountLessThan,
        BigDecimal amountGreaterThan,
        PayoutStatus status,
        Date createdBefore,
        Date createdAfter,
        Date paidBefore,
        Date paidAfter,
        Integer pageSize
    ) {
        // creating the pagable object
        Pageable pageable = pageSize != null ? 
            PageRequest.of(0, pageSize) : 
            Pageable.unpaged();
        
        Page<Payout> payouts = payoutRepository.findPayoutsWithFilters(id, accountId, walletId, amountLessThan, amountGreaterThan, status, createdBefore, createdAfter, paidBefore, paidAfter, pageable);

        return payouts.getContent()
            .stream()
            .map(FetchPayoutDto::new)
            .collect(Collectors.toList());
    }
}
