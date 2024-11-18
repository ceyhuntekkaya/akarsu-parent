package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Project;
import com.genixo.akarsu.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    final ProjectRepository repository;



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

    public Project setArchive(Long projectId, Boolean archive) {
        Project project = repository.findById(projectId).orElse(null);
        if(project == null) {
            return null;
        }
        project.setArchived(archive);
        return repository.saveAndFlush(project);
    }

    public List<Project> findProjectByAuth(Long type) {
        return repository.findProjectByAuth(type, true);
    }


    public List<Project> findProjects(Long type, Boolean archived) {
        return repository.findProjectByAuth(type, archived);
    }

    public List<Project> findAll(Long type) {
        return repository.findAllByType(type);
    }
}
