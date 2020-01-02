package com.example.PostawIWygraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PostawIWygraj.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long>{
    

}
