package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("Select a FROM Transaction a WHERE a.document.id= :documentId")
    List<Transaction> findByDocumentId(Long documentId);

    @Query("Select a FROM Transaction a WHERE a.document.id= :documentId and a.userTo.id= :userToId")
    Optional<Transaction> findByUserIdAndDocumentId(Long userToId, Long documentId);

    @Query("Select a FROM Transaction a WHERE a.sendDate is null and a.userTo.id= :userId")
    List<Transaction> myDocuments(Long userId);
}
