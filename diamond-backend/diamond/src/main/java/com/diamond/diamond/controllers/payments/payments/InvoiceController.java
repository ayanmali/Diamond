package com.diamond.diamond.controllers.payments.payments;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.new_payments.NewInvoiceDto;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Invoice;
import com.diamond.diamond.services.CustomerService;
import com.diamond.diamond.services.VendorService;

@RestController
@RequestMapping("/payments/invoices")
public class InvoiceController extends PaymentController<Invoice, NewInvoiceDto> {
    private final CustomerService customerService;

    public InvoiceController(VendorService vendorService, CustomerService customerService) {
        this.vendorService = vendorService;
        this.customerService = customerService;
    }

    @Override
    Invoice convertNewDtoToPayment(NewInvoiceDto paymentDto) {
        // TODO Auto-generated method stub
        List<VendorWallet> vendorWallets = this.getVendorWalletsFromPaymentDto(paymentDto);

        Invoice invoice = new Invoice(paymentDto.getAmount(), 
                                      vendorService.findVendorById(paymentDto.getVendorId()), 
                                      customerService.findCustomerById(paymentDto.getCustomerId()), 
                                      paymentDto.getCurrency(), 
                                      paymentDto.getChain(), 
                                      vendorWallets, 
                                      paymentDto.getVendorComments());
        return invoice;
    }
    
}
