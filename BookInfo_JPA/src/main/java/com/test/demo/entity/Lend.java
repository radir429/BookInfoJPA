package com.test.demo.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="lend")
public class Lend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant startOn;
	private Instant dueOn;
	
	@Enumerated(EnumType.ORDINAL)
	private LendStatus status;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	@JsonManagedReference
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	@JsonManagedReference
	private Member member;

}
