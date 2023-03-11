package com.bank.ebanking_backend.repositories;


import com.bank.ebanking_backend.entites.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
}
