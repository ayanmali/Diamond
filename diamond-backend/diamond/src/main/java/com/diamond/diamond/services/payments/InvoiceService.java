package com.diamond.diamond.services.payments;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchInvoiceDto;
import com.diamond.diamond.entities.payments.Invoice;
import com.diamond.diamond.entities.user.Customer;
import com.diamond.diamond.repositories.payments.InvoiceRepository;

@Service
public class InvoiceService extends PaymentService<Invoice> {

    public InvoiceService(InvoiceRepository invoiceRepository) {
        super(invoiceRepository);
    }

    @Override
    public FetchInvoiceDto convertPaymentToFetchDto(Invoice invoice) {
        FetchInvoiceDto invoiceDto =  new FetchInvoiceDto(invoice);

        // invoiceDto.setTimePaid(invoice.getTimePaid());
        // invoiceDto.setTimeSent(invoice.getTimeSent());
        // invoiceDto.setUpdatedAt(invoice.getUpdatedAt());
        // invoiceDto.setAccountComments(invoice.getAccountComments());

        // if (invoice.getAccount() != null && Hibernate.isInitialized(invoice.getAccount())) {
        //     invoiceDto.setAccountId(invoice.getAccount().getId());
        // }

        return invoiceDto;
    }

    public Invoice updateCustomer(UUID id, Customer customer) {
        Invoice invoice = this.paymentRepository.findById(id).orElseThrow();
        invoice.setCustomer(customer);
        return this.paymentRepository.save(invoice);
    }

    // public Invoice updateTimeSent(UUID id, Date timeSent) {
    //     Invoice invoice = this.paymentRepository.findById(id).orElseThrow();
    //     invoice.setTimeSent(timeSent);
    //     return this.paymentRepository.save(invoice);
    // }

    public Invoice updateTimePaid(UUID id, Date timePaid) {
        Invoice invoice = this.paymentRepository.findById(id).orElseThrow();
        invoice.setTimePaid(timePaid);
        return this.paymentRepository.save(invoice);
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

    // public void getAccountComments() {

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

    // public void setAccountComments() {

    // }

    // public void setCustomerComments() {

    // }

}