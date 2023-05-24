package com.mycom.trip.dto.favorite;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.mycom.trip.dto.TripDto;

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
public class FavoriteResultDto {
	
	private List<FavoriteGetDto> favoriteGetDtoList; 
	private int result; // 쿼리 실패 유무
	private int count; // favoriteGetDtolist.size();
}
