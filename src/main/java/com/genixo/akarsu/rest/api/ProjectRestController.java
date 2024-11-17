package com.genixo.akarsu.rest.api;

import com.genixo.akarsu.domain.Project;
import com.genixo.akarsu.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/project")
public class ProjectRestController {
    final ProjectService projectService;


    @GetMapping(value = "/auth/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> findProjectByAuth(@PathVariable("type") Long type) {
        List<Project> result = projectService.findProjectByAuth(type);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/active/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> findProjectByActive() {
        List<Project> result = projectService.findActiveProjects();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
