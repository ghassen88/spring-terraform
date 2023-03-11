package com.bank.ebanking_backend.services;


import com.bank.ebanking_backend.dtos.*;
import com.bank.ebanking_backend.entites.*;
import com.bank.ebanking_backend.enums.OperationType;
import com.bank.ebanking_backend.exception.BalanceNotSuffisantException;
import com.bank.ebanking_backend.exception.BankAccountNotFoundException;
import com.bank.ebanking_backend.exception.CustomerNotFoundException;
import com.bank.ebanking_backend.mappers.BankAccounMpaper;
import com.bank.ebanking_backend.repositories.BankAccountRepository;
import com.bank.ebanking_backend.repositories.CustomerRepository;
import com.bank.ebanking_backend.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 *
 * couche service
 * pour compte/customer/operation
 * @author  Ghassen boussaa
 * @version 1.0
 * @since   2022-12-03
 **/


@Service
@AllArgsConstructor
// tous les operations sont transactionnel
@Transactional
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {


    private CustomerRepository customerRepository;
    private OperationRepository operationRepository;

    private BankAccountRepository bankAccountRepository;
    private BankAccounMpaper bankAccounMpaper;


    /**
     *
     * les méthodes
     * de customer
     *
     **/

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        log.info("save new customer");

        if(customerDto == null){
            log.info ("customer not valid!!");
        }

        Customer customer= bankAccounMpaper.fromCustomerDto(customerDto);
        Customer saveCustomer= customerRepository.save(customer);

        return bankAccounMpaper.fromCustomer(saveCustomer);
    }
    @Override
    public List<CustomerDto> listCustomers() {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerDto> customerDtoList =customers.stream().map(customer -> bankAccounMpaper.fromCustomer(customer)).collect(Collectors.toList());
        log.info(String.valueOf(customerDtoList));
        return customerDtoList;
    }


    @Override
    public CustomerDto getCustomerById(Long customerId) {



        Customer customer =customerRepository.findById(customerId).orElseThrow(()-> new  CustomerNotFoundException("customer not found"));
        return  bankAccounMpaper.fromCustomer(customer);

    }


    @Override
    public CustomerDto updatCustomer(CustomerDto customerDto){

        log.info("save new customer");

        if(customerDto == null){
            log.info ("customer not valid!!");
        }

        Customer customer= bankAccounMpaper.fromCustomerDto(customerDto);
        Customer saveCustomer= customerRepository.save(customer);

        return bankAccounMpaper.fromCustomer(saveCustomer);
    }


    @Override
    public void deleteCustomer(Long customerId){
        if(customerId == null){
            log.info("customer not introuvable");
        }
        customerRepository.deleteById(customerId);

    }



    /**
     *
     * les méthodes
     * du compte
     *
     **/




    @Override
    public SavingBankAccountDto creatSavedAccount(double initialBalance, double intersetRat, Long CustomerId) throws ClassNotFoundException {
        Customer customer =customerRepository.findById(CustomerId).orElse(null);

        if(customer ==null){
            //il faut créer une exception métier
            throw  new RuntimeException("Customer not found");
        }

         SavingAccount bankAccount = new SavingAccount();

        bankAccount.setId(Long.valueOf(UUID.randomUUID().toString()));
        bankAccount.setCreatedAT(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setInterestRate(intersetRat);
        bankAccount.setCustomer(customer);

        SavingAccount savingAccount = bankAccountRepository.save(bankAccount);

        return bankAccounMpaper.fromSavingBankAccount(savingAccount);
    }

    @Override
    public CurrentBankAccountDto creatCurrentAccount(double initialBalance, double overDraft, Long CustomerId) throws CustomerNotFoundException {
        Customer customer =customerRepository.findById(CustomerId).orElse(null);

        if(customer ==null){
            //il faut créer une exception métier
            throw  new RuntimeException("Customer not found");
        }

        CurrentAccount bankAccount = new CurrentAccount();

        bankAccount.setId(Long.valueOf(UUID.randomUUID().toString()));
        bankAccount.setCreatedAT(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setOverDraft(overDraft);
        bankAccount.setCustomer(customer);

        return bankAccounMpaper.frpmCurrentAccount(bankAccount);
    }


    @Override
    public BankAccountDto getBankAccount(Long accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount =bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccountNotFound"));


        if(bankAccount instanceof  SavingAccount){
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return  bankAccounMpaper.fromSavingBankAccount(savingAccount);
        }else{

            CurrentAccount currentAccount =  (CurrentAccount) bankAccount;
            return  bankAccounMpaper.frpmCurrentAccount(currentAccount);

        }

    }

    @Override
    public List<BankAccountDto> bankAccountDtos() {

        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

        List<BankAccountDto> bankAccountDtoList =bankAccounts.stream().map(bankAccount -> {

            if(bankAccount instanceof  SavingAccount){
                SavingAccount savingAccount = (SavingAccount) bankAccount ;
               return bankAccounMpaper.fromSavingBankAccount(savingAccount);

            }else {
      //???
                CurrentAccount currentAccount = (CurrentAccount)  bankAccount;
               return  bankAccounMpaper.frpmCurrentAccount(currentAccount);

            }

        }).collect(Collectors.toList());

     return  bankAccountDtoList;
    }



    /*
    @Override
    public BankAccount creatAccount(double initialBalance, String type, Long CustomerId) throws CustomerNotFoundException {

        Customer customer =customerRepository.findById(CustomerId).orElse(null);

        if(customer ==null){
            //il faut créer une exception métier
            throw  new RuntimeException("Customer not found");
        }

        BankAccount bankAccount;

        if(type.equals("current")){
            bankAccount = new CurrentAccount();
        } else{
            bankAccount = new SavingAccount();
        }

        bankAccount.setId(Long.valueOf(UUID.randomUUID().toString()));
        bankAccount.setCreatedAT(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setCustomer(customer);

        return null;
    }

     */



    /**
     *
     * les méthodes
     * des operations
     *
     **/



    private BankAccount testAccount(Long  accountId){


        return bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccountNotFound"));
    }


    @Override
    public void debit(Long accountId, double amount, String description) throws BankAccountNotFoundException ,BalanceNotSuffisantException{




        if(testAccount(accountId).getBalance() <amount)
            throw new BalanceNotSuffisantException("solde non suffisant");

        Operation operation = new Operation();
        operation.setType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setBankAccount(testAccount(accountId));
        operation.setDate(new Date());
        operationRepository.save(operation);
        testAccount(accountId).setBalance(testAccount(accountId).getBalance() - amount);
        bankAccountRepository.save(testAccount(accountId));

    }

    @Override
    public void credit(Long accountId, double amount, String description) throws  BankAccountNotFoundException {

        BankAccount bankAccount =bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccountNotFound"));


        Operation operation = new Operation();
        operation.setType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setBankAccount(bankAccount);
        operation.setDate(new Date());
        operationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void transfer(Long accountIdSource, Long accountIdDestination, double amount) throws  BankAccountNotFoundException{



        debit(accountIdSource,amount,"transfer to " +accountIdDestination);
        credit(accountIdDestination,amount,"transfer from " +accountIdSource);
    }

  @Override
   public List<OperationDto> accountHistory(Long bankAccountId){
       List<Operation> operations = operationRepository.findByBankAccountId(bankAccountId);
       return operations.stream().map(operation -> bankAccounMpaper.fromOperation(operation)).collect(Collectors.toList());
   }

   //50%

    @Override
    public AccountHistoryDto getaccountHistory(Long accountId, int page, int size) {

       BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
       if(bankAccount == null) throw new BankAccountNotFoundException("account non trouvé");

        Page<Operation> operationPage =operationRepository.findByBankAccountId(accountId, PageRequest.of(page,size));
        AccountHistoryDto accountHistoryDto = new AccountHistoryDto();
        List<OperationDto> dtoList =operationPage.getContent().stream().map(operation -> bankAccounMpaper.fromOperation(operation)).collect(Collectors.toList());
        accountHistoryDto.setOperationDtoList(dtoList);
        accountHistoryDto.setAccountId(bankAccount.getId());
        accountHistoryDto.setBalance(bankAccount.getBalance());
        accountHistoryDto.setCurrentPage(page);
        accountHistoryDto.setPageSize(size);
        accountHistoryDto.setTotalPages(operationPage.getTotalPages());

        return accountHistoryDto;
    }


}
