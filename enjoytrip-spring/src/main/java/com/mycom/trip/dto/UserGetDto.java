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
public class UserGetDto {

	// 정보 열람시 보여줄 데이터, password는 없음
	private int userSeq;
	private String userName;
	private String userEmail;
	private Date userRegisterDate;

	private String userProfileImageUrl;

	private int birthYear;
	private int birthMonth;
	private int birthDay;

	// 아래는 공통코드에서 fk로 이름을 가져와야함
	private int genderCode;
	private String genderName; // gender table

	private int sidoCode;
	private String sidoName; // sido Table
	private int gugunCode;
	private String gugunName; // gugun Table

	private int userClsfCode;
	private String userClsfName; // group_code table
}