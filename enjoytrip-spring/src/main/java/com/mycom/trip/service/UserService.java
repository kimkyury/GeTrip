package com.mycom.trip.service;

import com.mycom.trip.dto.UserGetDto;
import com.mycom.trip.dto.UserPostDto;
import com.mycom.trip.dto.UserPutPasswordDto;
import com.mycom.trip.dto.UserPutProfileDto;
import com.mycom.trip.dto.UserSuccessResultDto;

public interface UserService {
	UserSuccessResultDto userRegister(UserPostDto userPostDto);
	
	UserGetDto userDetail(int userSeq);

	UserSuccessResultDto userUpdateProfile( UserPutProfileDto userPutProfileDto);

	UserSuccessResultDto userUpdatePassword( UserPutPasswordDto userPutPasswordDto);

	UserSuccessResultDto userDelete(int userSeq);

}
