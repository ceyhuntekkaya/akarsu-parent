package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Document;
import com.genixo.akarsu.domain.Transaction;
import com.genixo.akarsu.domain.User;
import com.genixo.akarsu.dto.DocumentSentDto;
import com.genixo.akarsu.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    final TransactionRepository repository;

    public List<Transaction> findByDocumentId(Long documentId) {
        return repository.findByDocumentId(documentId);
    }


    /*
 islemler
    islemlerEvrakAra
gonder
     evrakIslemTamamla
      okundu
     */

    public Transaction complate(Long id, Long userId) {
        Transaction transaction = findById(id);
        User user = new User();
        user.setId(userId);
        assert transaction != null;
        transaction.setUserTo(user);
        transaction.setSendDate(new Date(System.currentTimeMillis()));
        return repository.saveAndFlush(transaction);
    }

    public Transaction readed(Long id) {
        Transaction transaction = findById(id);
        assert transaction != null;
        transaction.setReadDate(new Date(System.currentTimeMillis()));
        return repository.saveAndFlush(transaction);
    }

    public Transaction send(Long transactionId, Long documentId, Long userFromId, Long userToId, String note, Boolean isCopy) {
        if (!isCopy) {
            Transaction transaction = findById(transactionId);
            assert transaction != null;
            transaction.setSendDate(new Date(System.currentTimeMillis()));
            repository.saveAndFlush(transaction);
        }
        Transaction transaction = repository.findByUserIdAndDocumentId(userToId, documentId).orElse(null);
        if (transaction == null) {
            transaction = new Transaction();
            Document document = new Document();
            document.setId(documentId);
            User userTo = new User();
            userTo.setId(userToId);
            User userFrom = new User();
            userFrom.setId(userFromId);
            transaction.setDocument(document);
            transaction.setUserFrom(userFrom);
            transaction.setUserTo(userTo);
            transaction.setCreateDate(new Date(System.currentTimeMillis()));
            transaction.setNote(note);
            transaction = repository.saveAndFlush(transaction);
        }
        return transaction;
    }


    public Transaction findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Transaction> myDocuments(Long userId) {
        return repository.myDocuments(userId);
    }

    public void documentSend(DocumentSentDto documentSentDto) {
        send(documentSentDto.getTransactionId(), documentSentDto.getDocumentId(), documentSentDto.getSenderId(), documentSentDto.getReceiverId(), documentSentDto.getNote(), documentSentDto.getIsCopy());
    }
}
