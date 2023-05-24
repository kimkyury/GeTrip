package com.mycom.trip.dto.favorite;

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
public class HotplaceResultDto {
	
	List<HotplaceGetDto> hotplaceGetDtoList;
	private int result; // 쿼리 실패 유무
	private int count; // hotplaceGetDtoList.size();
}
