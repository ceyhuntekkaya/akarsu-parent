package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("Select a FROM Authority a WHERE a.user.id= :userId")
    Authority findByUserId(@Param("userId") Long userId);


    @Query("Select a FROM Authority a WHERE a.user.username= :username and a.user.password= :password")
    Authority login(@Param("username") String username, @Param("password") String password);
}
