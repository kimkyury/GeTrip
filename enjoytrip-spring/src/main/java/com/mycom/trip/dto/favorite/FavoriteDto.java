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
public class FavoriteDto {
	
	// DB와 일치하는 기본 DTO
	private int favoriteId;
	private int userSeq;
	private int contentId;
	private String state;
	private LocalDateTime updatedDate;

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = LocalDateTime.ofInstant(updatedDate.toInstant(), ZoneId.systemDefault());
	}

}
