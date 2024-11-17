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
@Table(name = "islemlerim")
public class Transaction {

    @Id
    @Column(name = "kayitId")
    Long id;
    @ManyToOne
    @JoinColumn(name = "evrakNo")
    Document document;
    @ManyToOne
    @JoinColumn(name = "gonderen")
    User userFrom;
    @ManyToOne
    @JoinColumn(name = "personel")
    User userTo;
    @Column(name = "tarih")
    Date createDate;
    @Column(name = "okunma")
    Date readDate;
    @Column(name = "notu")
    String note;
    @Column(name = "gonderilmeTarihi")
    Date sendDate;


}
