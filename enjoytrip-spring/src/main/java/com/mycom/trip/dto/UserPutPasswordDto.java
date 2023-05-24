package com.mycom.trip.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPutPasswordDto {
	
	// Query의 where절에 필요한 Email
	private String userEmail;
	
	// 수정할 수 있는 data
	private String userPassword;
	private String userUpdatePassword;
	
}