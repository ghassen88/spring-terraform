package com.bank.ebanking_backend.entites;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("CUR")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CurrentAccount extends  BankAccount{

    private double overDraft;
}
