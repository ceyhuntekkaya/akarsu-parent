package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("Select a FROM Project a WHERE a.archived = :archived")
    List<Project> findActiveProjects(boolean archived);
}
