package com.mycom.trip.service;

import com.mycom.trip.dto.favorite.FavoriteDto;
import com.mycom.trip.dto.favorite.FavoriteResultDto;
import com.mycom.trip.dto.favorite.HotplaceResultDto;

public interface FavoriteService {
	FavoriteResultDto createFavorite(FavoriteDto favoriteDto ); // 사실 생성과 업데이트(state : 1, 0 )가 여기서 둘 다 이루어짐
	FavoriteResultDto getFavoriteList(int userSeq); // 유저의 좋아요 list 확인
	
	HotplaceResultDto getHotplaceList(); // 전국 hotPlace확인
	HotplaceResultDto getHotplaceListFromUser(int userSeq); // 유저 지역별 hotPlace확인
}
