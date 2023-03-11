package com.bank.ebanking_backend.dtos;


import com.bank.ebanking_backend.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentBankAccountDto extends BankAccountDto {




    private Long id;
    private Date CreatedAT;
    private double balance;
    private AccountStatus status;
    private CustomerDto customerDto;
    private double overDraft;
}
