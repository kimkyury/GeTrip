package com.mycom.trip.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.trip.dto.UserDto;
import com.mycom.trip.dto.UserGetDto;
import com.mycom.trip.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE,
		RequestMethod.PUT })
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping("/login")
	public Map<String, String> login(@RequestBody UserDto dto,  HttpSession session) {
		System.out.println("dtp: " + dto);
		UserGetDto userGetDto = loginService.login(dto);

		System.out.println("userGetDto: " + userGetDto.getUserSeq());
		Map<String, String> map = new HashMap<>();

		if (userGetDto != null) {
			session.setAttribute("userDto", userGetDto);
			System.out.println(userGetDto.toString());

			map.put("result", "success");

			map.put("userSeq", String.valueOf(userGetDto.getUserSeq()));
			map.put("userName", userGetDto.getUserName());
			map.put("userEmail", userGetDto.getUserEmail());
			System.out.println(userGetDto.getUserRegisterDate().toString());
			map.put("userRegisterDate", userGetDto.getUserRegisterDate().toString());

			map.put("userProfileImageUrl", userGetDto.getUserProfileImageUrl());

			map.put("userBirthYear", String.valueOf(userGetDto.getBirthYear()));
			map.put("userBirthMonth", String.valueOf(userGetDto.getBirthMonth()));
			map.put("userBirthDay", String.valueOf(userGetDto.getBirthDay()));

			map.put("genderCode", String.valueOf(userGetDto.getGenderCode()));
			map.put("genderName", userGetDto.getGenderName());

			map.put("userSidoCode", String.valueOf(userGetDto.getSidoCode()));
			map.put("userSidoName", userGetDto.getSidoName());

			map.put("userGugunCode", String.valueOf(userGetDto.getGugunCode()));
			map.put("userGugunName", userGetDto.getGugunName());

			map.put("userClsfCode", String.valueOf(userGetDto.getUserClsfCode()));
			map.put("userClsfName", userGetDto.getUserClsfName());

		} else {
			map.put("result", "fail");
		}
		return map;
	}
	
	@GetMapping("/login/kakao/{userEmail}")
	public Map<String, String> kakaoLoginSearch(@PathVariable String userEmail, HttpSession session){
		Map<String, String> map = new HashMap<>();		
		UserDto dto = new UserDto();
		dto.setUserEmail(userEmail);
		UserGetDto userGetDto = loginService.login(dto);
		
		if (userGetDto != null) {
			session.setAttribute("userDto", userGetDto);
			map.put("result", "success");

			map.put("userSeq", String.valueOf(userGetDto.getUserSeq()));
			map.put("userName", userGetDto.getUserName());
			map.put("userEmail", userGetDto.getUserEmail());
			map.put("userRegisterDate", userGetDto.getUserRegisterDate().toString());

			map.put("userProfileImageUrl", userGetDto.getUserProfileImageUrl());

			map.put("userBirthYear", String.valueOf(userGetDto.getBirthYear()));
			map.put("userBirthMonth", String.valueOf(userGetDto.getBirthMonth()));
			map.put("userBirthDay", String.valueOf(userGetDto.getBirthDay()));

			map.put("genderCode", String.valueOf(userGetDto.getGenderCode()));
			map.put("genderName", userGetDto.getGenderName());

			map.put("userSidoCode", String.valueOf(userGetDto.getSidoCode()));
			map.put("userSidoName", userGetDto.getSidoName());

			map.put("userGugunCode", String.valueOf(userGetDto.getGugunCode()));
			map.put("userGugunName", userGetDto.getGugunName());

			map.put("userClsfCode", String.valueOf(userGetDto.getUserClsfCode()));
			map.put("userClsfName", userGetDto.getUserClsfName());

		}else {
			map.put("result","fail");
		}
		
		return map;
	}
}