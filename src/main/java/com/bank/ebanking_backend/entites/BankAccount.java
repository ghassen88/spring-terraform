package com.bank.ebanking_backend.entites;


import com.bank.ebanking_backend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE", length =4)
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "bankaccount")
public abstract class BankAccount {
   @Id
   @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;
    private Date CreatedAT;
    //solde
    private double balance;
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.EAGER)
    private List<Operation> operationList;

}
