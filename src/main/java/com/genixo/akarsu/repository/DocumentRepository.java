package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {


    @Query("Select a FROM Document a WHERE (a.number like %:number% or a.subject like %:subject% or a.ocr like %:word% or a.type like %:type% or a.group like %:group%) and a.archive= :archive")
    List<Document> find(String number, String subject, String word, String type, String group, Boolean archive);

    @Query("Select a FROM Document a WHERE (a.number like %:number% or a.subject like %:subject% or a.ocr like %:word% or a.type like %:type% or a.group like %:group%) and a.archive= :archive and a.project.id = :projectId and a.documentDate >= :dateBegin and a.documentDate <= :dateEnd " )
    List<Document> findProjectAndDate(String number, String subject, String word, String type, String group, Boolean archive, Long projectId, Date dateBegin, Date dateEnd);

    @Query("Select a FROM Document a WHERE (a.number like %:number% or a.subject like %:subject% or a.ocr like %:word% or a.type like %:type% or a.group like %:group%) and a.archive= :archive and a.project.id = :projectId" )
    List<Document> findProject(String number, String subject, String word, String type, String group, Boolean archive, Long projectId);

    @Query("Select a FROM Document a WHERE (a.number like %:number% or a.subject like %:subject% or a.ocr like %:word% or a.type like %:type% or a.group like %:group%) and a.archive= :archive and a.documentDate >= :dateBegin and a.documentDate <= :dateEnd " )
    List<Document> findDate(String number, String subject, String word, String type, String group, Boolean archive, Date dateBegin, Date dateEnd);

    @Query("Select a FROM Document a WHERE a.number like %:number% or a.subject like %:subject% or a.ocr like %:word% or a.type like %:type% or a.group like %:group%")
    List<Document> findWord(String number, String subject, String word, String type, String group);

    @Query("Select a FROM Document a WHERE a.project.id= :projectId and a.number= :number")
    Optional<Document> findByProjectAndNumber(Long projectId, String number);

    @Query("Select a FROM Document a WHERE a.owner.id= :userId")
    List<Document> findByUser(Long userId);

    @Query("Select a FROM Document a WHERE a.project.id= :projectId and a.owner= :userId")
    List<Document> findByUserAndProject(Long userId, Long projectId);

    @Query("Select a FROM Document a WHERE a.project.id= :projectId")
    List<Document> findByProjectId(Long projectId);
}
