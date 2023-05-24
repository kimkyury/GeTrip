package com.mycom.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.trip.dao.LoginDao;
import com.mycom.trip.dto.UserDto;
import com.mycom.trip.dto.UserGetDto;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;

	@Override
	public UserGetDto login(UserDto userDto) {
		
		UserGetDto userGetdto = loginDao.login(userDto.getUserEmail());
		
		return userGetdto;
	}
}
