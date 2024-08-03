package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("Select a FROM User a WHERE a.username= :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("Select a FROM User a WHERE a.username= :username and a.password= :password")
    User findByLogin(String username, String password);

    @Query("Select a FROM User a WHERE a.status=true ")
    List<User> findByActive();
}
