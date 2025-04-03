package com.diamond.diamond.controllers.payments.payments;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.new_payments.NewInvoiceDto;
import com.diamond.diamond.entities.payments.Invoice;
import com.diamond.diamond.services.payments.InvoiceService;
import com.diamond.diamond.services.user.AccountService;
import com.diamond.diamond.services.user.AccountWalletService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController extends PaymentController<Invoice, NewInvoiceDto> {
    //private final CustomerService customerService;

    public InvoiceController(InvoiceService invoiceService, AccountService accountService, AccountWalletService accountWalletService) {
        super(invoiceService, accountService, accountWalletService);
    }

    @Override
    Invoice convertNewDtoToPayment(NewInvoiceDto paymentDto) {
        // TODO Auto-generated method stub
        //List<AccountWallet> accountWallets = this.getAccountWalletsFromPaymentDto(paymentDto);

        return new Invoice(paymentDto.getAmount(), 
                           paymentDto.getAccountId(), 
                           paymentDto.getCustomerId(),  
                           paymentDto.getChain(), 
                           paymentDto.getAccountWalletIds(), 
                           paymentDto.getAccountComments(),
                           paymentDto.getCurrencies());
    }

    // TODO: override getWithFilters endpoint for invoices
    
}
