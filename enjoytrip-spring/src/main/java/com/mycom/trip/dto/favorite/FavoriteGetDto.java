package com.mycom.trip.dto.favorite;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
public class FavoriteGetDto {
	
	// Query로 리턴되는 Favorite 단일 정보
	// Vue에서 유저의 sido, gugun이 저장되어있으므로 또 꺼낼 필요는 없음
	
	private int contentId; 				// attraction_info
	private String title; 				// attraciton_info
	private String addr1;			 	// attraction_info
	private String firstImage; 			// attraction_info
	private String overview; 			// attraction_description
	private LocalDateTime updatedDate; 	// favorite_attraction
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = LocalDateTime.ofInstant(updatedDate.toInstant(), ZoneId.systemDefault());
	}
}
