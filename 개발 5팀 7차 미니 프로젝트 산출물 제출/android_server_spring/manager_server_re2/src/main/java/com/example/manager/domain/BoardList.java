package com.example.manager.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class BoardList {

	@Getter @Setter
	@Id
	private List<Board> boardList;


}
