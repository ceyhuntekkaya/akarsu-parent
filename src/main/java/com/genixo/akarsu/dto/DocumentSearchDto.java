package com.genixo.akarsu.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class DocumentSearchDto {
    String number;
    String subject;
    String word;
    String type;
    String group;
    Long projectId;
    java.sql.Date dateBegin;
    Date dateEnd;
    Boolean archive;
}
