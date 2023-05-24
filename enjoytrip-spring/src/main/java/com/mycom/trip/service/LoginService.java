package com.mycom.trip.service;

import com.mycom.trip.dto.UserDto;
import com.mycom.trip.dto.UserGetDto;

public interface LoginService {

	UserGetDto login(UserDto userDto);

}
