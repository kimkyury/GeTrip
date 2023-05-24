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
public class TripDto {
	private int contentId;
	private String title;
	private String addr1;
	private String firstImage;
	private String overview;
	private int sidoCode;
	private String sidoName;
	private int gugunCode;
	private String gugunName;
	private java.math.BigDecimal latitude;
	private java.math.BigDecimal longitude; 
}
