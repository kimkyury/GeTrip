package com.mycom.trip.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.trip.dto.UserDto;
import com.mycom.trip.dto.UserGetDto;
import com.mycom.trip.dto.UserPostDto;
import com.mycom.trip.dto.UserPutPasswordDto;
import com.mycom.trip.dto.UserPutProfileDto;

@Mapper
public interface UserDao {
	int userRegister(UserPostDto userPostDto);
	
	UserGetDto userDetail(int userSeq);

	int userUpdateProfile( UserPutProfileDto userPutProfileDto);
	
	int userUpdatePassword( UserPutPasswordDto userPutPasswordDto); //email, password

	int userDelete(int userSeq);


}
