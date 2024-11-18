package com.genixo.akarsu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "yetkiler")
public class Authority {
    @Id
    @Column(name = "yetkiId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "personelId", unique = true)
    User user;
    @Column(name = "evrakArama")
    Boolean fileSearch;
    @Column(name = "arsiv")
    Boolean archive;
    @Column(name = "tarama")
    Boolean scan;
    @Column(name = "admin")
    Boolean admin;
    @Column(name = "metin")
    Boolean text;
    @Column(name = "proje")
    Boolean project;
    @Column(name = "silme")
    Boolean delete;

}
