package com.example.repository;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    boolean existsByUsername(String username);

    Optional<Account> findByUsernameAndPassword(String username, String password);
}
