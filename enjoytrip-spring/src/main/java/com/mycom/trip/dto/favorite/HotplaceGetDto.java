package com.mycom.trip.dto.favorite;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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


public class HotplaceGetDto {
	
	private int contentId;		// attration_info
	private String title;		// attration_info
	private String addr1;		// attration_info
	private String firstImage; 	// attration_info
	private String overview; 	// attration_description
	private int favoriteCnt; 	// subQuery로 count(*) 컬럼 생성
}
