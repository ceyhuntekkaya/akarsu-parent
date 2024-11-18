package com.genixo.akarsu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "docs")
public class DocumentFile {

    @Id
    @Column(name = "docId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "kayitId")
    Document document;
    @Column(name = "ad")
    String name;
    @Column(name = "tur")
    String type;


}
