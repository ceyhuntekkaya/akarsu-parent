package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {


    @Query("Select a FROM Document a WHERE (a.number ilike %:number% and a.subject ilike %:subject% and a.ocr ilike %:word% and a.type ilike %:type% and a.group ilike %:group%) and a.archive= :archive order by a.recordDate desc")
    List<Document> find(@Param("number") String number, @Param("subject") String subject, @Param("word") String word, @Param("type") String type, @Param("group") String group, @Param("archive") Boolean archive);

    @Query("Select a FROM Document a WHERE (a.number ilike %:number% and a.subject ilike %:subject% and a.ocr ilike %:word% and a.type ilike %:type% and a.group ilike %:group%) and a.archive= :archive and a.project.id = :projectId and a.documentDate >= :dateBegin and a.documentDate <= :dateEnd  order by a.recordDate desc" )
    List<Document> findProjectAndDate(@Param("number") String number, @Param("subject") String subject, @Param("word") String word, @Param("type") String type, @Param("group") String group, @Param("archive") Boolean archive, @Param("projectId") Long projectId, @Param("dateBegin") Date dateBegin, @Param("dateEnd") Date dateEnd);

    @Query("Select a FROM Document a WHERE (a.number ilike %:number% and a.subject ilike %:subject% and a.ocr ilike %:word% and a.type ilike %:type% and a.group ilike %:group%) and a.archive= :archive and a.project.id = :projectId order by a.recordDate desc" )
    List<Document> findProject(@Param("number") String number, @Param("subject") String subject, @Param("word") String word, @Param("type") String type, @Param("group") String group, @Param("archive") Boolean archive, @Param("projectId") Long projectId);

    @Query("Select a FROM Document a WHERE (a.number ilike %:number% and a.subject ilike %:subject% and a.ocr ilike %:word% and a.type ilike %:type% and a.group ilike %:group%) and a.archive= :archive and a.documentDate >= :dateBegin and a.documentDate <= :dateEnd  order by a.recordDate desc" )
    List<Document> findDate(@Param("number") String number, @Param("subject") String subject, @Param("word") String word, @Param("type") String type, @Param("group") String group, @Param("archive") Boolean archive, @Param("dateBegin") Date dateBegin, @Param("dateEnd") Date dateEnd);

    @Query("Select a FROM Document a WHERE a.number ilike %:number% and a.subject ilike %:subject% and a.ocr ilike %:word% and a.type ilike %:type% and a.group ilike %:group% and a.archive= :archive order by a.recordDate desc")
    List<Document> findWord(@Param("number") String number, @Param("subject") String subject, @Param("word") String word, @Param("type") String type, @Param("group") String group, @Param("archive") Boolean archive);

    @Query("Select a FROM Document a WHERE a.project.id= :projectId and a.number= :number order by a.recordDate desc")
    Optional<Document> findByProjectAndNumber(@Param("projectId") Long projectId, @Param("number") String number);

    @Query("Select a FROM Document a WHERE a.project.archived = false and a.owner.id= :userId order by a.recordDate desc")
    List<Document> findByUser(@Param("userId") Long userId);

    @Query("Select a FROM Document a WHERE a.project.id= :projectId and a.owner= :userId order by a.recordDate desc")
    List<Document> findByUserAndProject(@Param("userId") Long userId, @Param("projectId") Long projectId);

    @Query("Select a FROM Document a WHERE a.project.id= :projectId order by a.recordDate desc")
    List<Document> findByProjectId(@Param("projectId") Long projectId);
}
