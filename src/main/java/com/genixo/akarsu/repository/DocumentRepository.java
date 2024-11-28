package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Document;
import com.genixo.akarsu.dto.DocumentSearchDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {


    default List<Document> searchByNonEmptyCriteria(DocumentSearchDto params) {
        // Specification oluştur
        Specification<Document> spec = DocumentSearchSpecification.dynamicSearch(params);

        // Tüm kriterlerin boş olup olmadığını kontrol et
        if (isAllCriteriaEmpty(params)) {
            return Collections.emptyList();
        }

        // Sıralama ile birlikte sonuçları getir
        Sort sort = Sort.by(Sort.Direction.DESC, "recordDate");
        return findAll(spec, sort);
    }

    // Tüm kriterlerin boş olup olmadığını kontrol eden yardımcı metod
    default boolean isAllCriteriaEmpty(DocumentSearchDto params) {
        return !hasValidDateRange(params) &&
                !hasValidProject(params) &&
                !StringUtils.hasText(params.getDocumentType()) &&
                !StringUtils.hasText(params.getDocumentGroup()) &&
                !StringUtils.hasText(params.getDocumentNumber()) &&
                !StringUtils.hasText(params.getSubject()) &&
                !StringUtils.hasText(params.getSearchWord());
    }

    // Tarih aralığı kontrolü
    default boolean hasValidDateRange(DocumentSearchDto params) {
        return params.getBeginAt() != null && params.getEndAt() != null;
    }

    // Proje kontrolü
    default boolean hasValidProject(DocumentSearchDto params) {
        return params.getProjectId() != null && params.getProjectId() > 0;
    }

    @Query("SELECT d FROM Document d WHERE d.project.id = :projectId")
    List<Document> findByProjectId(Long projectId);

    @Query("SELECT d FROM Document d WHERE d.project.id = :id AND d.number = :number")
    Optional<Document> findByProjectAndNumber(Long id, String number);


}
