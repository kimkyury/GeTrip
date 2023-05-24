package com.mycom.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.trip.dao.FavoriteDao;
import com.mycom.trip.dto.favorite.FavoriteDto;
import com.mycom.trip.dto.favorite.FavoriteGetDto;
import com.mycom.trip.dto.favorite.FavoriteResultDto;
import com.mycom.trip.dto.favorite.HotplaceGetDto;
import com.mycom.trip.dto.favorite.HotplaceResultDto;


@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	FavoriteDao favoriteDao;
	
	static final int SUCCESS = 1;
	static final int FAIL = -1;
	static final int NOT_EXIST = 0;
	
	
	// 많은 쿼리가 한 메소드에 일어나기때문에 Transactional 적용
	@Override
	@Transactional
	public FavoriteResultDto createFavorite(FavoriteDto favoriteDto) {
		
		FavoriteResultDto favoriteResultDto = new FavoriteResultDto();
		boolean isSuccess = false;
		
		try {
			// 1. 이미 존재하는지 확인한다
			int isExist = favoriteDao.isExistFavorite(favoriteDto);
			
			if (isExist == 0) {
				// 2. 존재하지 않는다면 create를 수행한다
				int result = favoriteDao.createFavorite(favoriteDto);
				if ( result == 1)
					isSuccess = true;
				
			}else {
				// 3. 이미 존재한다면 update를 수행한다
				int result = favoriteDao.updateFavorite(favoriteDto);
				if ( result == 1)
					isSuccess = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if ( isSuccess) 
			favoriteResultDto.setResult(SUCCESS);
		else 
			favoriteResultDto.setResult(FAIL);
		
		return favoriteResultDto;
	}

	@Override
	@Transactional
	public FavoriteResultDto getFavoriteList(int userSeq) {
		FavoriteResultDto favoriteResultDto = new FavoriteResultDto();
		
		boolean isSuccess = false;
		boolean isExist = false;
		
		try {
			// 1. user가 좋아요한 것이 있긴한지 확인
			int resultCnt = favoriteDao.cntFavoriteFromUser(userSeq);
			
			if ( resultCnt > 0) {
				isExist = true;

				// 2. 있으면 정보 저장
				List<FavoriteGetDto> favoriteGetDtoList = favoriteDao.getFavoriteGetDtoList(userSeq);
				favoriteResultDto.setFavoriteGetDtoList(favoriteGetDtoList);
				favoriteResultDto.setCount(favoriteGetDtoList.size());
				isSuccess = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if ( !isExist) {
			favoriteResultDto.setResult(NOT_EXIST);
		}else if (isSuccess) {
			favoriteResultDto.setResult(SUCCESS);
		}else {
			favoriteResultDto.setResult(FAIL);
		}
		
		return favoriteResultDto;
	}

	@Override
	public HotplaceResultDto getHotplaceList() {
		HotplaceResultDto hotplaceResultDto = new HotplaceResultDto();
		boolean isSuccess = false;
		
		try {
			List<HotplaceGetDto> hotplaceGetDtoList = favoriteDao.getHotplaceGetDtoList();
			hotplaceResultDto.setHotplaceGetDtoList(hotplaceGetDtoList);
			hotplaceResultDto.setCount(hotplaceGetDtoList.size());
			isSuccess = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if ( isSuccess) 
			hotplaceResultDto.setResult(SUCCESS);
		else 
			hotplaceResultDto.setResult(FAIL);
		
		return hotplaceResultDto;
	}

	@Override
	public HotplaceResultDto getHotplaceListFromUser(int userSeq) {
		HotplaceResultDto hotplaceResultDto = new HotplaceResultDto();
		boolean isSuccess = false;
		
		try {
			List<HotplaceGetDto> hotplaceGetDtoList = favoriteDao.getHotplaceGetDtoListFromUser(userSeq);
			hotplaceResultDto.setHotplaceGetDtoList(hotplaceGetDtoList);
			hotplaceResultDto.setCount(hotplaceGetDtoList.size());
			isSuccess = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if ( isSuccess) 
			hotplaceResultDto.setResult(SUCCESS);
		else 
			hotplaceResultDto.setResult(FAIL);
		
		return hotplaceResultDto;
	}	
}
