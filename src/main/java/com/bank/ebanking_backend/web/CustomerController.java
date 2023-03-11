package com.bank.ebanking_backend.web;


import com.bank.ebanking_backend.dtos.CustomerDto;
import com.bank.ebanking_backend.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
public class CustomerController {



    @Autowired
    private BankAccountService bankAccount;

   @GetMapping("/customers")
    public List<CustomerDto> getCustomers(){
        log.info("list customers affiché");
        return bankAccount.listCustomers();
    }


    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long CustomerId) throws  ClassNotFoundException{
        log.info("customer affiché");
       return bankAccount.getCustomerById(CustomerId);
    }

   @PostMapping("/customers/saveCustomer")
    public void  saveCustomer(@RequestBody CustomerDto request){
       log.info("customer ajouté avec success");
       try {
           CustomerDto customerDto =bankAccount.saveCustomer(request);

       } catch (Exception  e) {
           log.error("customer trouvé");
           e.printStackTrace();
       }

       //return bankAccount.saveCustomer(request);
    }

   @PutMapping("/customers/{idCustomer}")
    public CustomerDto updateCustomer(@PathVariable Long idCustomer,@RequestBody CustomerDto customerDto){
    customerDto.setId(idCustomer);

    log.info("customer supprimé avec succes");
    return bankAccount.updatCustomer(customerDto);
    }


    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
     log.info("customer supprimé avec succes");
       bankAccount.deleteCustomer(customerId);
    }



}
