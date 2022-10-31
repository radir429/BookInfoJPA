package com.test.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String isbn;
	
	
	@ManyToOne
	@JoinColumn(name="author_id")
	@JsonManagedReference
	private Author author;
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@JsonBackReference
	@OneToMany(mappedBy="book", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lend> lends;

}
