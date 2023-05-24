package com.mycom.trip.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.trip.dto.UserDto;
import com.mycom.trip.dto.UserGetDto;
import com.mycom.trip.dto.UserPostDto;
import com.mycom.trip.dto.UserPutPasswordDto;
import com.mycom.trip.dto.UserPutProfileDto;
import com.mycom.trip.dto.UserSuccessResultDto;
import com.mycom.trip.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE,
		RequestMethod.PUT })
public class UserController {
	@Autowired
	UserService service;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = 1;


	
	@PostMapping(value = "/users")
	public ResponseEntity<UserSuccessResultDto> userRegister(@RequestBody UserPostDto userPostDto) {
		System.out.println("[USER POST:]" + userPostDto);
		
		UserSuccessResultDto userResultDto = service.userRegister(userPostDto);

		if (userResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(userResultDto);
		} else {
			return new ResponseEntity(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/users/{userSeq}")
	public ResponseEntity<UserGetDto> userDetail(HttpSession session){
		UserDto dto = (UserDto) session.getAttribute("userDto");
		System.out.println("[USER GET: (in Session) ]" + dto);
		int userSeq = dto.getUserSeq();
		
		UserGetDto getDto = service.userDetail(userSeq);
		
		if( getDto != null ) {
			return ResponseEntity.ok().body(getDto);
		}else {
			return ResponseEntity.notFound().build();
		}			
	}
	
	
	@PutMapping(value = "/users/{userSeq}/profile")
	public  ResponseEntity<UserSuccessResultDto> userUpdateProfile(@PathVariable(value = "userSeq") int userSeq, UserPutProfileDto userPutProfileDto, HttpSession session) {
		System.out.println(userSeq);
		UserGetDto dto = (UserGetDto) session.getAttribute("userDto");
		System.out.println(userPutProfileDto.toString()); 
		UserSuccessResultDto userSuccessResultDto = new UserSuccessResultDto();

		if ( dto.getUserSeq() == userSeq ) {
			// 세션과 일치할 경우에만 작동
			userSuccessResultDto = service.userUpdateProfile(userPutProfileDto);
			
			if (userSuccessResultDto.getResult() == SUCCESS) {
				return ResponseEntity.ok().body(userSuccessResultDto);
			}
		}else {	
			userSuccessResultDto.setResult(FAIL);
		}
		
		return new ResponseEntity(userSuccessResultDto, HttpStatus.BAD_REQUEST);
	
	}
	
	@PutMapping(value = "/users/{userSeq}/password")
	public  ResponseEntity<UserSuccessResultDto> userUpdatePassword(@PathVariable(value = "userSeq") int userSeq, UserPutPasswordDto userPutPasswordDto, HttpSession session) {
		UserGetDto dto = (UserGetDto) session.getAttribute("userDto");
		UserSuccessResultDto userSuccessResultDto = new UserSuccessResultDto();
		
		if ( dto.getUserSeq() == userSeq ) {
			// 세션과 일치할 경우에만 작동
			userSuccessResultDto = service.userUpdatePassword(userPutPasswordDto);
			
			if (userSuccessResultDto.getResult() == SUCCESS) {
				return ResponseEntity.ok().body(userSuccessResultDto);
			}
		}else {	
			userSuccessResultDto.setResult(FAIL);
		}
		
		return new ResponseEntity(userSuccessResultDto, HttpStatus.BAD_REQUEST);
	}
	

	
	@DeleteMapping(value = "/users/{userSeq}")
	public ResponseEntity<UserSuccessResultDto> userDelete(@PathVariable(value="userSeq") int userSeq,  @RequestBody String userPassword, HttpSession session){
		UserDto dto = (UserDto) session.getAttribute("userDto");
		
		UserSuccessResultDto userSuccessResultDto = new UserSuccessResultDto();
		
		if ( dto.getUserSeq() == userSeq && dto.getUserPassword() == userPassword) {
			userSuccessResultDto = service.userDelete(userSeq);
			if ( userSuccessResultDto.getResult() == SUCCESS) {
				return ResponseEntity.ok().body(userSuccessResultDto);
			}
		}else {
			userSuccessResultDto.setResult(FAIL);
		}
		
		return new ResponseEntity(userSuccessResultDto, HttpStatus.BAD_REQUEST);
	}
}
