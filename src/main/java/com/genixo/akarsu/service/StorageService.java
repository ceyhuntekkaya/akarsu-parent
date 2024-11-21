package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.DocumentFile;
import com.genixo.akarsu.repository.DocumentFileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    final DocumentFileRepository documentFileRepository;

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);


    @Transactional
    public String saveToFile(
            MultipartFile file) {


        logger.info("File name Start: {}", file.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        Path rootPath = Paths.get(userBucketPath + "/Temp");
        createDirectoryIfNotExists(rootPath.toString());
        Path path = rootPath.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);
            System.out.println("(TRY IN)e: " + path);
            logger.info("File name: İşlem Tamam {}", path);
            return fileName;
        } catch (IOException e) {
            logger.info("File name: {}", e.getMessage());
            System.out.println("(IOException 1): " + path);
            System.out.println("(IOException 2): " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Resource loadFile(String fileName, String path) {
        try {
            Path brandRootPath = Paths.get(userBucketPath + path);
            createDirectoryIfNotExists(brandRootPath.toString());
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

    public Resource loadFile(Long id) {
        try {

            DocumentFile documentFile = documentFileRepository.findById(id).orElseThrow(() -> null);
            if (documentFile == null) {
                throw new RuntimeException("Could not read the file!");
            }
            String fileName = documentFile.getDocument().getId() + "/" + documentFile.getName();
            Path brandRootPath = Paths.get(userBucketPath + "/Docs/");
            createDirectoryIfNotExists(brandRootPath.toString());
            Path file = brandRootPath.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            System.out.println("(loadFile): " + file.toUri());
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

    public void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public Resource moveFile(String fileName, Long documentId) {
        try {
            Path tempRootPath = Paths.get(userBucketPath + "/Temp");
            Path brandRootPath = Paths.get(userBucketPath + "/Docs/" + documentId + "/");

            createDirectoryIfNotExists(tempRootPath.toString());
            createDirectoryIfNotExists(brandRootPath.toString());

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
