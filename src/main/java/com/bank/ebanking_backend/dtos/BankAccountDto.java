package com.bank.ebanking_backend.dtos;


import com.bank.ebanking_backend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {


    private String type;
}
