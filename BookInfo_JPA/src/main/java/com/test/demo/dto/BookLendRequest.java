package com.test.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class BookLendRequest {
	private List<Long> bookIds;
	private Long memberId;

}
