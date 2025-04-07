package com.moamenproject.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moamenproject.accounts.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long>{
    Optional<Accounts> findByCustomerId(Long customerID);
}
