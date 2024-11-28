package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Document;
import com.genixo.akarsu.dto.DocumentSearchDto;
import com.genixo.akarsu.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository repository;
    private final TransactionService transactionService;

    public List<Document> findAll() {
        return repository.findAll();
    }

    public List<Document> searchDocuments(DocumentSearchDto params) {
        return repository.searchByNonEmptyCriteria(params);
    }





    public Document findById(Long documentId) {
        return repository.findById(documentId).orElse(null);
    }

    public List<Document> findByProject(Long projectId) {
        return repository.findByProjectId(projectId);
    }

    public Document update(Document document) {
        /// TODO: LOG Add
        return repository.saveAndFlush(document);
    }

    public Document archive(Long transactionId, Long documentId, Boolean archive, Long userId) {
        Document document = repository.findById(documentId).orElse(null);
        assert document != null;
        document.setArchive(archive);
        /// TODO: LOG archive
        transactionService.complate(transactionId, userId);
        return repository.saveAndFlush(document);
    }


    public Document add(Document document) {
        /// TODO: LOG Add
        // sayı kontrol et
        // kendine gönder

        Document checkDocument = repository.findByProjectAndNumber(
                document.getProject().getId(),
                document.getNumber()
        ).orElse(null);

        document.setNumber(document.getNumber().toUpperCase());
        document.setSubject(document.getSubject().toUpperCase());
        document.setOcr(document.getOcr().toUpperCase());
        document.setType(document.getType().toUpperCase());
        document.setGroup(document.getGroup().toUpperCase());


        if (checkDocument == null) {
            Document createdDocument = repository.saveAndFlush(document);
            transactionService.send(null, createdDocument.getId(), document.getOwner().getId(),
                    document.getOwner().getId(), "", true);
            return document;
        }
        return null;
    }

    public void deleteByDocumentFileId(Long documentId) {

    }

    public void deleteByDocumentId(Long documentId) {
        repository.deleteById(documentId);
    }

    public Document updateDocument(Long documentId, Document document) {
        Document document1 = repository.findById(documentId).orElse(null);
        assert document1 != null;
        document1.setNumber(document.getNumber());
        document1.setSubject(document.getSubject());
        document1.setOcr(document.getOcr());
        document1.setType(document.getType());
        document1.setGroup(document.getGroup());
        document1.setArchive(document.getArchive());
        document1.setOwner(document.getOwner());
        document1.setProject(document.getProject());
        document1.setConnected(document.getConnected());
        document1.setDocumentAddress(document.getDocumentAddress());
        document1.setDocumentDate(document.getDocumentDate());
        return repository.saveAndFlush(document1);

    }



    /*
 evrakArama
    evrakAramaArsiv
    evrakAramaProje
    evrakAramaProjeArsiv
    evrakAramaProjeTarih
    evrakAramaProjeTarihArsiv
    evrakAramaSil
    evrakAramaTarih
    evrakAramaTarihArsiv
    evrakArsivle
    evrakGuncelle
    evrakGuncelleArama

    evrakKaydet
    evrakTek
    evraklarim
    evraklarimProje


    pdfHazirla
     */
}
