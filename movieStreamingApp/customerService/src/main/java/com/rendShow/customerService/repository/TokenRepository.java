package com.rendShow.customerService.repository;

import com.rendShow.customerService.pojo.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
            select t from Token t inner join Customers c on t.customers.id = c.id
            where c.id = :id and (t.expired = false or t.revoked = false)
            """)
   List<Token> findAllValidTokensByUser(Long id);

    Optional<Token> findByToken(String token);
}
