package com.bank.ebanking_backend.mappers;


import com.bank.ebanking_backend.dtos.CurrentBankAccountDto;
import com.bank.ebanking_backend.dtos.CustomerDto;
import com.bank.ebanking_backend.dtos.OperationDto;
import com.bank.ebanking_backend.dtos.SavingBankAccountDto;
import com.bank.ebanking_backend.entites.CurrentAccount;
import com.bank.ebanking_backend.entites.Customer;
import com.bank.ebanking_backend.entites.Operation;
import com.bank.ebanking_backend.entites.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
//MapStruct c'est un framework qui fait le mapping des données
public class BankAccounMpaper {




    //customer
    public CustomerDto fromCustomer(Customer customer){


        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);

        //customerDto.setId(customer.getId());
        //customerDto.setName(customer.getName());
        //customerDto.setEmail(customer.getEmail());

        return  customerDto;
    }

    public Customer fromCustomerDto(CustomerDto customerDto){


        Customer  customer = new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        //customer.setId(customerDto.getId());
        //customer.setName(customerDto.getName());

        return customer;
    }


    //compte epargne

    public SavingBankAccountDto fromSavingBankAccount(SavingAccount savingAccount){

         SavingBankAccountDto savingBankAccountDto = new SavingBankAccountDto();
         BeanUtils.copyProperties(savingAccount,savingBankAccountDto);
         savingBankAccountDto.setCustomerDto(fromCustomer(savingAccount.getCustomer()));
         savingBankAccountDto.setType(savingAccount.getClass().getSimpleName());
         return  savingBankAccountDto;
    }



    public SavingAccount fromSavingAccountDto(SavingBankAccountDto savingBankAccountDto){


          SavingAccount savingAccount = new SavingAccount();
          BeanUtils.copyProperties(savingBankAccountDto,savingAccount);
          savingAccount.setCustomer(fromCustomerDto(savingBankAccountDto.getCustomerDto()));
          return savingAccount;
    }


    //compte courant



    public CurrentBankAccountDto frpmCurrentAccount(CurrentAccount currentAccount){

       CurrentBankAccountDto currentBankAccountDto = new CurrentBankAccountDto();
       BeanUtils.copyProperties(currentAccount,currentBankAccountDto);
       currentBankAccountDto.setCustomerDto(fromCustomer(currentAccount.getCustomer()));
       currentBankAccountDto.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDto;

    }


    public CurrentAccount fromCurrentAccountDto(CurrentBankAccountDto currentBankAccountDto){


        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDto,currentAccount);
        currentAccount.setCustomer(fromCustomerDto(currentBankAccountDto.getCustomerDto()));
        return currentAccount;
    }


    //Operation

      public OperationDto fromOperation(Operation operation){

        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operation,operationDto);

        return operationDto;
      }



      public Operation fromOperationDto(OperationDto operationDto){


        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDto,operation);
        return operation;
      }

}
