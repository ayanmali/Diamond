package com.diamond.diamond.controllers.payments.payments;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.new_payments.NewInvoiceDto;
import com.diamond.diamond.entities.payments.Invoice;
import com.diamond.diamond.entities.user.AccountWallet;
import com.diamond.diamond.services.user.AccountService;
import com.diamond.diamond.services.user.CustomerService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController extends PaymentController<Invoice, NewInvoiceDto> {
    private final CustomerService customerService;

    public InvoiceController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @Override
    Invoice convertNewDtoToPayment(NewInvoiceDto paymentDto) {
        // TODO Auto-generated method stub
        List<AccountWallet> accountWallets = this.getAccountWalletsFromPaymentDto(paymentDto);

        Invoice invoice = new Invoice(paymentDto.getAmount(), 
                                      accountService.findAccountById(paymentDto.getAccountId()), 
                                      customerService.findCustomerById(paymentDto.getCustomerId()),  
                                      paymentDto.getChain(), 
                                      accountWallets, 
                                      paymentDto.getAccountComments(),
                                      paymentDto.getCurrencies());
        return invoice;
    }

    // TODO: override getWithFilters endpoint for invoices
    
}
