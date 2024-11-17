package com.genixo.akarsu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projeler")
public class Project {
    @Id
    @Column(name = "projeId")
    Long id;
    @Column(name = "projeAdi")
    String name;
    @Column(name = "aciklama")
    String description;
    @Column(name = "aktif")
    Boolean archived;
    @Column(name = "yetkiDuzeyi")
    Long authorizationLevel;
    @ManyToOne
    @JoinColumn(name = "acan")
    User createdBy;
    @Column(name = "tarih")
    Date creationDate;
}
