package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Document;
import com.genixo.akarsu.domain.Log;
import com.genixo.akarsu.domain.User;
import com.genixo.akarsu.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    /*

    logGoster
    logGosterEvrakAra
     */
    final LogRepository repository;

    public void addLog(Long documentId, Long userId, String transaction, String ip) {
        Document document = new Document();
        document.setId(documentId);
        User user = new User();
        user.setId(userId);
        Log log = new Log();
        log.setDocument(document);
        log.setUser(user);
        log.setTransaction(transaction);
        log.setRecordDate(new java.sql.Date(System.currentTimeMillis()));
        log.setIp(ip);
        repository.saveAndFlush(log);
    }

    public List<Log> findByDocumentId(Long documentId) {
        return repository.findByDocumentId(documentId);
    }
}