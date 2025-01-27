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
@Table(name = "metinler")
public class Text {
	@Id
	@Column(name = "metinId")
	Long id;
	@Column(name = "baslik")
	String header;
	@Column(name = "metin")
	String text;
	@Column(name = "tarih")
	Date createdDate;
	@ManyToOne
	@JoinColumn(name = "yazan")
	User createdBy;
	@Column(name = "durum")
    Boolean status;

}
