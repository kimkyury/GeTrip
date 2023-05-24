package com.mycom.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.trip.dao.UserDao;
import com.mycom.trip.dto.UserDto;
import com.mycom.trip.dto.UserGetDto;
import com.mycom.trip.dto.UserPostDto;
import com.mycom.trip.dto.UserPutPasswordDto;
import com.mycom.trip.dto.UserPutProfileDto;
import com.mycom.trip.dto.UserSuccessResultDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public UserSuccessResultDto userRegister(UserPostDto userPostDto) {
		UserSuccessResultDto userResultDto = new UserSuccessResultDto();

		if (userDao.userRegister(userPostDto) == SUCCESS) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}

		return userResultDto;
	}
	
	@Override
	public UserGetDto userDetail(int userSeq) {
		return userDao.userDetail(userSeq);
	}
	
	@Override
	public UserSuccessResultDto userUpdateProfile( UserPutProfileDto userPutProfileDto) {
		UserSuccessResultDto userResultDto = new UserSuccessResultDto();
		
		if (userDao.userUpdateProfile(userPutProfileDto) == SUCCESS) {
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	
	@Override
	public UserSuccessResultDto userUpdatePassword (UserPutPasswordDto userPutPasswordDto) {
		UserSuccessResultDto userResultDto = new UserSuccessResultDto();
		
		if (userDao.userUpdatePassword(userPutPasswordDto) == SUCCESS ) {
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
	
	
	@Override
	public UserSuccessResultDto userDelete(int userSeq) {
		UserSuccessResultDto userResultDto = new UserSuccessResultDto();
		
		if (userDao.userDelete(userSeq) == SUCCESS ) {
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		
		
		return userResultDto;
	}

	
}
