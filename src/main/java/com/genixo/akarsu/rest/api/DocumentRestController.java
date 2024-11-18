package com.genixo.akarsu.rest.api;

import com.genixo.akarsu.domain.*;
import com.genixo.akarsu.dto.DocumentDetailDto;
import com.genixo.akarsu.dto.DocumentSearchDto;
import com.genixo.akarsu.dto.DocumentSentDto;
import com.genixo.akarsu.service.DocumentFileService;
import com.genixo.akarsu.service.DocumentService;
import com.genixo.akarsu.service.LogService;
import com.genixo.akarsu.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/document")
public class DocumentRestController {
    final DocumentService documentService;
    final TransactionService transactionService;
    final LogService logService;
    final DocumentFileService documentFileService;

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDetailDto>> myDocuments(@PathVariable("userId") Long userId) {
        List<DocumentDetailDto> result = new ArrayList<>();
        List<Transaction> transactions = transactionService.myDocuments(userId);
        for (Transaction transaction : transactions) {
            if (transaction.getDocument() != null) {
                List<Log> logs = logService.findByDocumentId(transaction.getDocument().getId());
                List<DocumentFile> files = documentFileService.findByDocumentId(transaction.getDocument().getId());
                DocumentDetailDto documentDetailDto = new DocumentDetailDto();
                documentDetailDto.setDocument(transaction.getDocument());
                documentDetailDto.setLogs(logs);
                documentDetailDto.setFiles(files);
                result.add(documentDetailDto);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Document>> findAll() {
        List<Document> result = documentService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/document/{documentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> findById(@PathVariable("documentId") Long documentId) {
        Document result = documentService.findById(documentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/document/{documentId}/files/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentFile>> findByIdFiles(@PathVariable("documentId") Long documentId) {
        Document result = documentService.findById(documentId);
        List<DocumentFile> files = documentFileService.findByDocumentId(result.getId());
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping(value = "/document/{documentId}/logs/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Log>> findByIdLogs(@PathVariable("documentId") Long documentId) {
        Document result = documentService.findById(documentId);
        List<Log> logs = logService.findByDocumentId(result.getId());
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @GetMapping(value = "/project/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Document>> findByProject(@PathVariable("projectId") Long projectId) {
        List<Document> result = documentService.findByProject(projectId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{transactionId}/{documentId}/{archive}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> archive(@PathVariable("transactionId") Long transactionId, @PathVariable("documentId") Long documentId, @PathVariable("archive") Boolean archive, @PathVariable("userId") Long userId) {
        Document result = documentService.archive(transactionId, documentId, archive, userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> add(@RequestBody Document document) {
        Document documents = documentService.add(document);
        return new ResponseEntity<>(documents, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}/project/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Document>> findByUserAndProject(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId) {
        List<Document> result = documentService.findByUserAndProject(userId, projectId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/search/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Document>> documentSearch(@RequestBody DocumentSearchDto params) {
        List<Document> documents = documentService.documentSearch(params);
        return new ResponseEntity<>(documents, HttpStatus.CREATED);
    }


    @PostMapping(value = "/send/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> documentSend(@RequestBody DocumentSentDto documentSentDto) {
        transactionService.documentSend(documentSentDto);
        List<Transaction> result = transactionService.findByDocumentId(documentSentDto.getDocumentId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/transaction/{documentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("documentId") Long documentId) {
        List<Transaction> result = transactionService.findByDocumentId(documentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
