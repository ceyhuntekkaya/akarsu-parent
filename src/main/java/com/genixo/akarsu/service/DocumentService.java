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

    public List<Document> documentSearch(DocumentSearchDto params) {
        Date currentDate = new Date(System.currentTimeMillis());
        Date dateBegin = currentDate;
        Date dateEnd = currentDate;
        long projectId = 0L;

        String number = params.getDocumentNumber().toUpperCase();
        String subject = params.getSubject().toUpperCase();
        String word = params.getSearchWord().toUpperCase();
        String type = params.getDocumentType().toUpperCase();
        String group = params.getDocumentGroup().toUpperCase();

        if (params.getProjectId() > 0) {
            projectId = params.getProjectId();
        }
        if (params.getBeginAt() != null) {
            dateBegin = params.getBeginAt();
        }
        if (params.getBeginAt() != null) {
            dateEnd = params.getEndAt();
        }
        Boolean archive = params.getArchive();

        if (projectId == 0 && dateBegin == currentDate && dateEnd == currentDate) {
            return repository.find(number, subject, word, type, group, archive);
        } else if (projectId > 0 && dateBegin != currentDate && dateEnd != currentDate) {
            return repository.findProjectAndDate(number, subject, word, type, group, archive, projectId, dateBegin, dateEnd);
        } else if (projectId > 0 && dateBegin == currentDate && dateEnd == currentDate) {
            return repository.findProject(number, subject, word, type, group, archive, projectId);
        } else if (projectId == 0 && dateBegin != currentDate && dateEnd != currentDate) {
            return repository.findDate(number, subject, word, type, group, archive, dateBegin, dateEnd);
        } else {
            return repository.findWord(number, subject, word, type, group, archive);
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
