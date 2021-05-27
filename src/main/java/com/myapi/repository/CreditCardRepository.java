package com.myapi.repository;

import com.myapi.model.creditCard.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    Boolean existsByNumber(String number);
}
