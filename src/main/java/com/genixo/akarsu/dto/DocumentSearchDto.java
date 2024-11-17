package com.genixo.akarsu.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentSearchDto {
    private Date beginAt;
    private Date endAt;
    private Long projectId;
    private String documentType;
    private String documentGroup;
    private String documentNumber;
    private String subject;
    private String searchWord;
    private Boolean hasDate;
    private Boolean hasProject;
    private Long authority;
    private Boolean archive;
}
