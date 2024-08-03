package com.genixo.akarsu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personeller")
public class User {
    @Id
    @Column(name = "personelId")
    Long id;
    @Column(name = "personelAd")
	String name;
    @Column(name = "birim")
    String unit;
    @Column(name = "kullanici")
    String username;
    @Column(name = "sifre")
    String password;
    @Column(name = "durum")
    Boolean status;
    @Column(name = "yetki")
    Long authority;

}
