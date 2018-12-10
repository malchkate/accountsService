package com.splat.AccountService.repository;

import com.splat.AccountService.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert ignore into accounts (id, amount) values (:id, 0)", nativeQuery = true)
    void insertIfNotExist(@Param("id") int id);

    @Modifying
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    @Query(value = "update Account a set a.amount = :amount where a.id = :id")
    void updateAccount(@Param("id") int id, @Param("amount") Long amount);

    @Modifying
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    @Query(value = "update Account a set a.amount = a.amount + :value where a.id = :id")
    void incrementAmount(@Param("id") int id, @Param("value") Long value);
}
