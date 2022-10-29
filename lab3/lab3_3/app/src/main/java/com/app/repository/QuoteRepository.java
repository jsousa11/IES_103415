package com.app.repository;

import com.app.model.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote,Integer> {
    Quote findByAvaliacao(String title);
}
