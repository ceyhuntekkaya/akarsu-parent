package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.DocumentFile;
import com.genixo.akarsu.repository.DocumentFileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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

    public void deleteByDocumentFileId(Long documentId) {
        repository.deleteById(documentId);

    }




    /*
 docs
    docsEvrakAra
    doscEkle
    doscSil
    dosyalistesi
     */




}
