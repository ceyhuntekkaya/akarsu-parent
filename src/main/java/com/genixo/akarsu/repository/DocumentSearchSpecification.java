package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Document;
import com.genixo.akarsu.dto.DocumentSearchDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class DocumentSearchSpecification {

    public static Specification<Document> dynamicSearch(DocumentSearchDto params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getBeginAt() != null && params.getEndAt() != null) {
                predicates.add(criteriaBuilder.between(
                        root.get("documentDate"),
                        params.getBeginAt(),
                        params.getEndAt()
                ));
            }

            if (params.getProjectId() != null && params.getProjectId() > 0) {
                predicates.add(criteriaBuilder.equal(
                        root.get("project").get("id"),
                        params.getProjectId()
                ));
            }

            if (params.getArchive() != null && !params.getArchive()) {
                predicates.add(criteriaBuilder.equal(
                        root.get("archive"),
                        params.getArchive()
                ));
            }

            if (params.getDocumentType() != null && !params.getDocumentType().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        root.get("type"),
                        params.getDocumentType()
                ));
            }




            if (StringUtils.hasText(params.getDocumentGroup())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("group")),
                        "%" + params.getDocumentGroup() + "%"
                ));
            }

            if (StringUtils.hasText(params.getDocumentNumber())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("number")),
                        "%" + params.getDocumentNumber().toLowerCase() + "%"
                ));
            }

            if (StringUtils.hasText(params.getSubject())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("subject")),
                        "%" + params.getSubject().toLowerCase() + "%"
                ));
            }

            if (StringUtils.hasText(params.getSearchWord())) {
                Predicate projectNamePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("project").get("name")),
                        "%" + params.getSearchWord().toLowerCase() + "%"
                );

                Predicate documentNamePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("number")),
                        "%" + params.getSearchWord().toLowerCase() + "%"
                );

                Predicate documentSubjectPredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("subject")),
                        "%" + params.getSearchWord().toLowerCase() + "%"
                );

                predicates.add(criteriaBuilder.or(projectNamePredicate, documentNamePredicate, documentSubjectPredicate));
            }

            query.orderBy(criteriaBuilder.desc(root.get("recordDate")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
