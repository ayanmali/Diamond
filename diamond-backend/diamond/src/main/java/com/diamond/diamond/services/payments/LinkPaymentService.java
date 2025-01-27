package com.diamond.diamond.services.payments;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.LinkPayment;
import com.diamond.diamond.repositories.payments.LinkPaymentRepository;

@Service
public class LinkPaymentService {
    private final LinkPaymentRepository linkPaymentRepository;

    public LinkPaymentService(LinkPaymentRepository linkPaymentRepository) {
        this.linkPaymentRepository = linkPaymentRepository;
    }

    public LinkPayment saveLinkPayment(LinkPayment linkPayment) {
        return linkPaymentRepository.save(linkPayment);
    }

    public Optional<LinkPayment> findLinkPaymentById(UUID id) {
        return linkPaymentRepository.findById(id);
    }

    public void deletePaymentById(UUID id) {
        linkPaymentRepository.deleteById(id);
    }

}