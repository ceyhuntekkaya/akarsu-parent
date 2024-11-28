package com.genixo.akarsu.repository;

import com.genixo.akarsu.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("Select a FROM Project a WHERE a.authorizationLevel <= :type and a.archived = :archived order by a.name")
    List<Project> findProjectByAuth(@Param("type") Long type, @Param("archived") boolean archived);

    @Query("Select a FROM Project a WHERE a.authorizationLevel <= :type order by a.archived desc , a.name")
    List<Project> findAllByType(@Param("type") Long type);


    @Query("Select a FROM Project a WHERE a.authorizationLevel <= :type order by a.name")
    List<Project> findProjectAll(@Param("type") Long type);



    @Query("Select a FROM Project a WHERE a.authorizationLevel <= :type and a.archived = true order by a.name")
    List<Project> findActive(@Param("type") Long type);

    @Query("Select a FROM Project a WHERE a.authorizationLevel <= :type and a.archived = false order by a.name")
    List<Project> findNonActive(@Param("type") Long type);
}
