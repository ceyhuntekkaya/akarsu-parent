package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Document;
import com.genixo.akarsu.domain.Project;
import com.genixo.akarsu.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    final ProjectRepository repository;
    final DocumentService documentService;




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
        return repository.findProjectAll(type);
    }


    public List<Project> findProjects(Long type, Boolean archived) {
        if(archived == true){
            return repository.findActive(type);
        }
        return repository.findNonActive(type);
    }

    public List<Project> findAll(Long type) {
        return repository.findAllByType(type);
    }

    public void deleteByProjectId(Long projectId) {
        List<Document> documents = documentService.findByProject(projectId);
        if(documents.isEmpty()) {
            repository.deleteById(projectId);
        }
    }

    public Project updateProject(Long projectId, Project project) {
        Project project1 = repository.findById(projectId).
                orElse(null);
        if(project1 == null) {
            return null;
        }
        project1.setArchived(project.getArchived());
        return repository.saveAndFlush(project1);
    }
}
