package com.genixo.akarsu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "log")
public class Log {
    @Id
    @Column(name = "logId")
    Long id;
    @ManyToOne
    @JoinColumn(name = "evrakId")
    Document document;
    @ManyToOne
    @JoinColumn(name = "personel")
    User user;
    @Column(name = "islem")
    String transaction;
    @Column(name = "tarih")
    Date recordDate;
    @Column(name = "ip")
    String ip;
}
