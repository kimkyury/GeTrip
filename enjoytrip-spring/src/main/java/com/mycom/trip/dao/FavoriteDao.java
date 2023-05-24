package com.mycom.trip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.trip.dto.favorite.FavoriteDto;
import com.mycom.trip.dto.favorite.FavoriteGetDto;
import com.mycom.trip.dto.favorite.HotplaceGetDto;

@Mapper
public interface FavoriteDao {
	
	// create시, 이미 userSeq와 contentId가 있는지 확인하고 생성
	// 만약 이미 userSeq와 contentId가 일치하는 것이 확인된다면 updateFavorite실행
	int isExistFavorite (FavoriteDto favoriteDto);
	int createFavorite (FavoriteDto favoriteDto);
	int updateFavorite (FavoriteDto favoriteDto);
	
	
	int cntFavoriteFromUser (int userSeq); // 유저가 활성화한 좋아요의 개수 확인
	List<FavoriteGetDto> getFavoriteGetDtoList(int userSeq) ;
	
	List<HotplaceGetDto> getHotplaceGetDtoList(); // 전국 상위 9개 추출
	List<HotplaceGetDto> getHotplaceGetDtoListFromUser(int userSeq); // 유저의 맞춤지역 상위 9개 추출
}
