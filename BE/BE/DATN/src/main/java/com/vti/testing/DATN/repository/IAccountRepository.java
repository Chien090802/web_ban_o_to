package com.vti.testing.DATN.repository;

import com.vti.testing.DATN.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAccountRepository extends JpaRepository<Account,Integer>, JpaSpecificationExecutor<Account> {
    boolean existsByUsername(String username);

	Account findByUsername(String username);
}
