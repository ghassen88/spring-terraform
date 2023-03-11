package com.bank.ebanking_backend.dtos;


import com.bank.ebanking_backend.enums.OperationType;
import lombok.Data;

import java.util.Date;


@Data
public class OperationDto {



    private Long id;
    private Date date;
    private String description;
    private double amount;
    private OperationType type;
    //@private BankAccountDto bankAccountDto;
}
