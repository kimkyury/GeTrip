package com.mycom.trip.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.trip.dto.BoardDto;
import com.mycom.trip.dto.BoardParamDto;
import com.mycom.trip.dto.BoardResultDto;

public interface BoardService {
	// 목록
	BoardResultDto boardList(BoardParamDto boardParamDto);
	// 상세
	BoardResultDto boardDetail(BoardParamDto boardParamDto);
	// 생성
	BoardResultDto boardInsert(BoardDto boardDto);
	// 수정
	BoardResultDto boardUpdate(BoardDto boardDto);
	// 삭제
	BoardResultDto boardDelete(int boardId);

}
