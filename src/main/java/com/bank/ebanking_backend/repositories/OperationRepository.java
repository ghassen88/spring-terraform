package com.bank.ebanking_backend.repositories;


import com.bank.ebanking_backend.entites.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {


    List<Operation> findByBankAccountId(Long accountId);
    Page<Operation> findByBankAccountId(Long accountId, Pageable pageable);

}
