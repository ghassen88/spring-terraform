package com.bank.ebanking_backend.entites;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("SA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccount  extends  BankAccount{
    //taux d'intert
    private double interestRate;


}
