package com.Java6.JPA;

import java.util.List;

import com.Java6.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.account.username=?1")
    List<Order> findByUsername(String username);
    
}
