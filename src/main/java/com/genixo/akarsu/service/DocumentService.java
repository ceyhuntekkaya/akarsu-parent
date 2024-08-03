package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Document;
import com.genixo.akarsu.dto.DocumentSearchDto;
import com.genixo.akarsu.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository repository;
    private final TransactionService transactionService;

    public List<Document> findAll() {
        return repository.findAll();
    }

    public List<Document> documentSearch(DocumentSearchDto params) {
        String number = params.getNumber();
        String subject = params.getSubject();
        String word = params.getWord();
        String type = params.getType();
        String group = params.getGroup();
        Long projectId = params.getProjectId();
        java.sql.Date dateBegin = params.getDateBegin();
        Date dateEnd = params.getDateEnd();
        Boolean archive = params.getArchive();
        if (projectId == null && dateBegin == null && dateEnd == null) {
            return repository.find(number, subject, word, type, group, archive);
        } else if (projectId != null && dateBegin != null && dateEnd != null) {
            return repository.findProjectAndDate(number, subject, word, type, group, archive, projectId, dateBegin, dateEnd);
        } else if (projectId != null && dateBegin == null && dateEnd == null) {
            return repository.findProject(number, subject, word, type, group, archive, projectId);
        } else if (projectId == null && dateBegin != null && dateEnd != null) {
            return repository.findDate(number, subject, word, type, group, archive, dateBegin, dateEnd);
        } else {
            return repository.findWord(number, subject, word, type, group);
        }
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

    public Document archive(Long transactionId, Long documentId , Boolean archive, Long userId) {
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

        if (checkDocument == null) {
            Document createdDocument = repository.saveAndFlush(document);
            transactionService.send(null, createdDocument.getId(), document.getOwner().getId(),
                    document.getOwner().getId(), "", true);
            return document;
        }
        return null;
    }

    public List<Document> findByUser(Long userId) {
        return repository.findByUser(userId);
    }

    public List<Document> findByUserAndProject(Long userId, Long projectId) {
        return repository.findByUserAndProject(userId, projectId);
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
