package com.eazy.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazy.accounts.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}
