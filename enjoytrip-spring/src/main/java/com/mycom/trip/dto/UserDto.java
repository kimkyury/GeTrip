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
public class UserDto {
	private int userSeq;
	private String userName;
	private String userPassword;
	private String userEmail;

	private Date userRegisterDate;

	/* 0518 Add */
	private String userProfileImageUrl;

	private int birthYear;
	private int birthMonth;
	private int birthDay;

	
	// 아래는 공통코드에서 fk로 이름을 가져와야함
	private int gender; // gender table
	private int sidoCode; // sido Table
	private int gugunCode; // gugun Table
	private String userClsf; // group_code table
}