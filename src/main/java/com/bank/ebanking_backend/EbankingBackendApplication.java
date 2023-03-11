package com.bank.ebanking_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class EbankingBackendApplication {





	public static void main(String[] args) {

		SpringApplication.run(EbankingBackendApplication.class, args);
	}

/*

@Bean
	CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, OperationRepository operationRepository){

		return  args ->{
			Stream.of("hassan","yassine","naim").forEach(name -> {
						Customer customer = new Customer();
						customer.setName(name);
						customer.setEmail(name+"@gmail.com");
						customerRepository.save(customer);



					}
			);

			customerRepository.findAll().forEach(customer -> {

				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreatedAT(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(customer);
				currentAccount.setOverDraft(9000);
				bankAccountRepository.save(currentAccount);

			});

			customerRepository.findAll().forEach(customer -> {

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAT(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(customer);
				savingAccount.setInterestRate(5.5);
				bankAccountRepository.save(savingAccount);

			});

			bankAccountRepository.findAll().forEach(acc->{
				for(int i = 0;i<10;i++){

					Operation operation = new Operation();
					operation.setDate(new Date());
					operation.setAmount(Math.random()*1200);
					operation.setType(Math.random()> 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
					operation.setBankAccount(acc);
					operationRepository.save(operation);
				}
			});




		};
	}
*/
}
