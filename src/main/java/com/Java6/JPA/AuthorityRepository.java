package com.Java6.JPA;

import java.util.List;

import com.Java6.entity.Account;
import com.Java6.entity.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    List<Authority> findByAccount(Account account);
}
