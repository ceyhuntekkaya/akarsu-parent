package com.genixo.akarsu.dto;

import lombok.Data;

@Data
public class SearchProjectDto {
    private Long beginAt;
    private Long endAt;
    private String projectName;
    private Long projectId;
    private String documentType;
    private String documentGroup;
    private String documentNumber;
    private String subject;
    private String searchWord;
    private Boolean hasDate;
    private Boolean hasProject;
    private Long authority;
}
