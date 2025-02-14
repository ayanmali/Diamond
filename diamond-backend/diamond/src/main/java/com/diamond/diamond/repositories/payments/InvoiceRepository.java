package com.diamond.diamond.repositories.payments;

import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.Invoice;

@Repository
public interface InvoiceRepository extends PaymentRepository<Invoice> {
    
}
