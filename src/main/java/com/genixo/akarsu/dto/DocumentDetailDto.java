package com.genixo.akarsu.dto;

import com.genixo.akarsu.domain.Document;
import com.genixo.akarsu.domain.DocumentFile;
import com.genixo.akarsu.domain.Log;
import lombok.Data;

import java.util.List;

@Data
public class DocumentDetailDto {
    Document document;
    List<Log> logs;
    List<DocumentFile> files;
}
