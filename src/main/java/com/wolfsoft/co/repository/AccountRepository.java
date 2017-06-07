package com.wolfsoft.co.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wolfsoft.co.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByUserName(String userName);
}
