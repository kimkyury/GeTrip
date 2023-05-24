package com.mycom.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardParamDto {
	// 목록
	private int limit;
	private int offset;
	private String searchWord;
	
	// 상세
	private int boardId;
	private int userSeq; // 세션
}

