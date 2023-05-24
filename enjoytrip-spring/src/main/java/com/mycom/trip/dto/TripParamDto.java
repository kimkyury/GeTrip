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
public class TripParamDto {
	private int contentId;
	private int limit;
	private int offset;
	private String searchWord;
	private int sidoCode;
	private int gugunCode;
	
}
