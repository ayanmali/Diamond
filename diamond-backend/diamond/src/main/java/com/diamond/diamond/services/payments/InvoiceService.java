package com.diamond.diamond.services.payments;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.Invoice;
import com.diamond.diamond.repositories.payments.InvoiceRepository;

@Service
public class InvoiceService extends PaymentService<Invoice> {

    public InvoiceService(InvoiceRepository invoiceRepository) {
        super(invoiceRepository);
    }

    // public Invoice saveInvoice(Invoice invoice) {
    //     return invoiceRepository.save(invoice);
    // }

    // public Optional<Invoice> findInvoiceById(UUID id) {
    //     return invoiceRepository.findById(id);
    // }

    // public void deletePaymentById(UUID id) {
    //     invoiceRepository.deleteById(id);
    // }

    // public void getTimeSent() {

    // }

    // public void getTimePaid() {

    // }

    // public void getLocationPaid() {

    // }

    // public void getVendorComments() {

    // }

    // public void getCustomerComments() {

    // }

    // /* */

    // public void setTimeSent() {

    // }

    // public void setTimePaid() {

    // }

    // public void setLocationPaid() {

    // }

    // public void setVendorComments() {

    // }

    // public void setCustomerComments() {

    // }

}