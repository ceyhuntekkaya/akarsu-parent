package com.genixo.akarsu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
public class StorageService {

    @Value("${storage.base}")
    private String userBucketPath;


    @Transactional
    public String saveToFile(
            MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        Path rootPath = Paths.get(userBucketPath + "Temp");
        Path path = rootPath.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Resource loadFile(String fileName, String path) {
        try {
            Path brandRootPath = Paths.get(userBucketPath + path);
            Path file = brandRootPath.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            System.out.println("file name: " + e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Resource loadFile(String fileName) {
        try {
            Path brandRootPath = Paths.get(userBucketPath + "Docs");
            Path file = brandRootPath.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            System.out.println("file name: " + e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Resource moveFile(String fileName, Long documentId) {
        try {
            Path tempRootPath = Paths.get(userBucketPath + "Tempp");
            Path brandRootPath = Paths.get(userBucketPath + "Docs/" + documentId);
            Path temp_file = tempRootPath.resolve(fileName);
            Path final_file = brandRootPath.resolve(fileName);

            Files.move(temp_file, final_file, REPLACE_EXISTING);
            Resource resource = new UrlResource(final_file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            System.out.println("file name: " + e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("file name: " + e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }


    }
}
