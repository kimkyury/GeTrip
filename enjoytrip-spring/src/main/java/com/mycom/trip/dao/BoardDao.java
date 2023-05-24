package com.mycom.trip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.trip.dto.BoardDto;
import com.mycom.trip.dto.BoardParamDto;
import com.mycom.trip.dto.BoardResultDto;

@Mapper
public interface BoardDao {
	// 목록
	List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardListTotalCount(); // 총 건수

	// 상세
	BoardDto boardDetail(BoardParamDto boardParamDto);

	// 생성
	int boardInsert(BoardDto dto);

	// 수정
	int boardUpdate(BoardDto dto);

	// 삭제
	int boardDelete(int boardId);

	// 조회수 증가
	int boardReadCountUpdate(int boardId);

}
