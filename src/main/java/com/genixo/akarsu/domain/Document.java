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
@Table(name = "evraklar")
public class Document {

    @Id
    @Column(name = "evrakId")
    Long id;
    @Column(name = "tur")
    String type;
    @Column(name = "grup")
    String group;
    @ManyToOne
    @JoinColumn(name = "proje")
    Project project;
    @Column(name = "tarih")
    Date documentDate;
    @Column(name = "sayi")
    String number;
    @Column(name = "konu")
    String subject;
    @Column(name = "yetkiDuzeyi")
    Long authorizationLevel;
    @Column(name = "kayitTarih")
    Date recordDate;
    @ManyToOne
    @JoinColumn(name = "kaydeden")
    User recordBy;
    @Column(name = "arsiv")
    Boolean archive;
    @Column(name = "evrakAdres")
    String documentAddress;
    @ManyToOne()
    @JoinColumn(name = "bagli")
    Document connected;
    @ManyToOne
    @JoinColumn(name = "sahip")
    User owner;
    @Column(name = "ocr")
    String ocr;

/*

    @Id
    @Column(name = "evrakId")
    Long id;
    @Column(name = "tur")
    String tur;
    @Column(name = "grup")
    String grup;
    @Column(name = "proje")
    Long project;
    @Column(name = "tarih")
    Date tarih;
    @Column(name = "sayi")
    String sayi;
    @Column(name = "konu")
    String konu;
    @Column(name = "yetkiDuzeyi")
    Long yetkiDuzeyi;
    @Column(name = "kayitTarih")
    Date kayitTarih;
    @Column(name = "kaydeden")
    Long kaydeden;
    @Column(name = "arsiv")
    Boolean arsiv;
    @Column(name = "evrakAdres")
    String evrakAdres;
    @Column(name = "bagli")
    Long bagli;
    @Column(name = "sahip")
    Long sahip;
    @Column(name = "ocr")
    String ocr;

 */
}
