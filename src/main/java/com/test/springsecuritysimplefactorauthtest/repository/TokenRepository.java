package com.test.springsecuritysimplefactorauthtest.repository;

import com.test.springsecuritysimplefactorauthtest.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByValue(String value);
}
