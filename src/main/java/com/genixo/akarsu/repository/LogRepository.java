package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    @Query("Select a FROM Log a WHERE a.document.id= :documentId")
    List<Log> findByDocumentId(Long documentId);
}
