package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.DocumentFile;
import com.genixo.akarsu.repository.DocumentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentFileService {
    final DocumentFileRepository repository;

    public List<DocumentFile> findByDocumentId(Long documentId) {
        return repository.findByDocumentId(documentId);
    }

    public void delete(DocumentFile file) {
        repository.delete(file);
    }

    public DocumentFile add(DocumentFile file) {
        /// TODO: Eksik - doscEkle
        return repository.saveAndFlush(file);
    }


    /*
 docs
    docsEvrakAra
    doscEkle
    doscSil
    dosyalistesi
     */
}
