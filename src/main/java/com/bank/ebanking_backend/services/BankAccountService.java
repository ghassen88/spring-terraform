package com.bank.ebanking_backend.services;


import com.bank.ebanking_backend.dtos.*;
import com.bank.ebanking_backend.exception.BankAccountNotFoundException;

import java.util.List;

public interface BankAccountService {




     CustomerDto saveCustomer(CustomerDto customerDto);

     SavingBankAccountDto creatSavedAccount(double initialBalance , double intersetRat , Long CustomerId) throws ClassNotFoundException;
     CurrentBankAccountDto creatCurrentAccount(double initialBalance , double overDraft , Long CustomerId) throws ClassNotFoundException;

     List<CustomerDto> listCustomers();

     CustomerDto getCustomerById(Long customerId);


     BankAccountDto getBankAccount(Long accountId) throws BankAccountNotFoundException;

     void debit(Long accountId, double amount, String description);
     void credit(Long accountId,double amount,String description);
     void transfer(Long accountIdSource,Long accountIdDestination, double amount);

     CustomerDto updatCustomer(CustomerDto customerDto);

     void deleteCustomer(Long customerId);

     List<BankAccountDto> bankAccountDtos();

    List<OperationDto> accountHistory(Long id);


     AccountHistoryDto getaccountHistory(Long accountId, int page, int size);
}
