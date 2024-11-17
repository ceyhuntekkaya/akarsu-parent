package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Project;
import com.genixo.akarsu.dto.SearchProjectDto;
import com.genixo.akarsu.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectService {
    final ProjectRepository repository;


    public List<Project> activeProjects() {
        /// TODO: tablo.Rows[i]["projeAdi"] = tablo.Rows[i]["projeAdi"].ToString().Substring(0, 40);
        return repository.findActiveProjects(true);
    }

    public List<Project> archiveProjects() {
        /// TODO: tablo.Rows[i]["projeAdi"] = tablo.Rows[i]["projeAdi"].ToString().Substring(0, 40);
        return repository.findActiveProjects(false);
    }

    public List<Project> findAll() {
        /// TODO: tablo.Rows[i]["projeAdi"] = tablo.Rows[i]["projeAdi"].ToString().Substring(0, 40);
        return repository.findAll();
    }

    public Project add(Project project) {
        return repository.saveAndFlush(project);
    }

    public Project update(Project project) {
        return repository.saveAndFlush(project);
    }

    public void delete(Project project) {
        /// TODO: hiç evrakı yoksa sil
        repository.delete(project);
    }

    public Project setArchive(Project project, Boolean archive) {
        project.setArchived(archive);
        return repository.saveAndFlush(project);
    }

    public List<Project> findProjectByAuth(Long type) {
        return repository.findProjectByAuth(type);
    }


    public List<Project> findActiveProjects() {
        return repository.findActiveProjects(false);
    }
}