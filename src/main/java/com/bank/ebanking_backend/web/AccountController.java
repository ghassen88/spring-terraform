package com.bank.ebanking_backend.web;


import com.bank.ebanking_backend.dtos.AccountHistoryDto;
import com.bank.ebanking_backend.dtos.BankAccountDto;
import com.bank.ebanking_backend.dtos.OperationDto;
import com.bank.ebanking_backend.exception.BankAccountNotFoundException;
import com.bank.ebanking_backend.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AccountController {


    private BankAccountService bankAccountService;

    public AccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


   @GetMapping("/accounts/{bankAccountId}")
    public BankAccountDto getBankAccount(@PathVariable Long bankAccountId)throws BankAccountNotFoundException {
       log.info("afficher compte bancaire");
        return  bankAccountService.getBankAccount(bankAccountId);
    }


    @GetMapping("/accounts")
    public List<BankAccountDto> bankAccountDtos(){
        log.info("afficher list des comptes");

        return bankAccountService.bankAccountDtos();
    }



    @GetMapping("/accounts/{accountId}/operations")
    public List<OperationDto> operationHistory(@PathVariable Long accountId){
        log.info("list des operations");

        return bankAccountService.accountHistory(accountId);
    }



    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDto getaccountHistory(@PathVariable Long accountId,
                                               @RequestParam(name = "page",defaultValue = "0") int page,
                                               @RequestParam(name = "size",defaultValue = "5") int size)throws BankAccountNotFoundException{

        return bankAccountService.getaccountHistory(accountId,page,size);
    }




}
