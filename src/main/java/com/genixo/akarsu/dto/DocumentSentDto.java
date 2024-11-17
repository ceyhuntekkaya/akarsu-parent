package com.genixo.akarsu.dto;

import lombok.Data;

@Data
public class DocumentSentDto {
    private Long documentId;
    private Long senderId;
    private Long receiverId;
    private String note;
    private Long transactionId;
    private Boolean isCopy;

    // Decimal evrakId, Decimal gonderen, Decimal personel, String notu, Decimal kayitId, Boolean kopyasiMi
}
