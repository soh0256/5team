package com.example.manager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
@Table(name = "BOARD")
public class Board {
	
	@Id
	@Column(name = "ID")
	private String writer;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "HITS")
	private int cnt;
	

}
