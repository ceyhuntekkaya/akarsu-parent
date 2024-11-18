package com.genixo.akarsu.rest.api;

import com.genixo.akarsu.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/storage")
public class StorageRestController {

    private final StorageService storageService;


    @PostMapping(value = "/upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        String createdFile = storageService.saveToFile(file);
        return new ResponseEntity<>(createdFile, HttpStatus.OK);
    }

    @GetMapping(value = "/preview/file/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> previewFile(@PathVariable("fileName") String fileName) {
        Resource file = storageService.loadFile(fileName );
        return new ResponseEntity<>(file, HttpStatus.OK);
    }
}
