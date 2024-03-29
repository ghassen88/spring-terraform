package com.bank.ebanking_backend.dtos;


import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDto {


    private Long accountId;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<OperationDto> operationDtoList;
}
