package com.example.BootApp.repo;

import com.example.BootApp.models.Account;
import com.example.BootApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
  Optional< Account> findByUsername(String username);

}
