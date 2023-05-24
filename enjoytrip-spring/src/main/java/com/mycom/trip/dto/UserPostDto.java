package com.mycom.trip.dto;

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
public class UserPostDto {
	
	// 생성시 필요한 데이터 
	private String userName;
	private String userPassword;
	private String userEmail;

	private String userProfileImageUrl;

	private int birthYear;
	private int birthMonth;
	private int birthDay;

	private int gender; 
	private int sidoCode; 
	private int gugunCode;
}