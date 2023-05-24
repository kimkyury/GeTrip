package com.mycom.trip.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.trip.dto.UserGetDto;

@Mapper
public interface LoginDao {

	UserGetDto login(String userEmail);

}
