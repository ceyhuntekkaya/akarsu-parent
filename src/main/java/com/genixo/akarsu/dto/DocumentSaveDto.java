package com.genixo.akarsu.dto;

import com.genixo.akarsu.domain.Document;
import lombok.Data;

import java.util.List;

@Data
public class DocumentSaveDto {
    Document document;
    List<String> files;
}
