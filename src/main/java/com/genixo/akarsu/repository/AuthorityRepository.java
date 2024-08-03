package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("Select a FROM Authority a WHERE a.user.id= :userId")
    Authority findByUserId(Long userId);


    @Query("Select a FROM Authority a WHERE a.user.username= :username and a.user.password= :password")
    Authority login(String username, String password);
}
