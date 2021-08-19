package com.Java6.JPA;

import java.util.List;

import com.Java6.entity.Account;
import com.Java6.entity.Authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    List<Authority> findByAccount(Account account);

    @Query("SELECT DISTINCT a FROM  Authority a WHERE a.account IN ?1")
    List<Authority> authoritiesOf(List<Account> accounts);
}
