package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.DocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentFileRepository extends JpaRepository<DocumentFile, Long> {
    @Query("Select a FROM DocumentFile a WHERE a.document.id= :documentId")
    List<DocumentFile> findByDocumentId(Long documentId);
}
