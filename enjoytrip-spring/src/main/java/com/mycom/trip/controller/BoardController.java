package com.mycom.trip.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.trip.dto.BoardDto;
import com.mycom.trip.dto.BoardParamDto;
import com.mycom.trip.dto.BoardResultDto;
import com.mycom.trip.dto.UserGetDto;
import com.mycom.trip.service.BoardService;

@RestController
@CrossOrigin(origins = "http://192.168.203.102:8080", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE,
		RequestMethod.PUT })
public class BoardController {

	@Autowired
	BoardService service;

	private static final int SUCCESS = 1;

	@GetMapping(value = "/boards")
	public ResponseEntity<BoardResultDto> boardList(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto;
		boardResultDto = service.boardList(boardParamDto);
		System.out.println("[Get-All]: " + boardResultDto);

		if (boardResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(boardResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/boards/{boardId}")
	public ResponseEntity<BoardResultDto> boardDetail(@PathVariable int boardId, HttpSession session) {

		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setUserSeq(((UserGetDto) session.getAttribute("userDto")).getUserSeq());
		boardParamDto.setBoardId(boardId);

		BoardResultDto boardResultDto = service.boardDetail(boardParamDto);
		System.out.println("[Get-Detail]: " + boardResultDto);
		// 게시글 작성자와 현 사용자가 동일
		if (((UserGetDto) session.getAttribute("userDto")).getUserSeq() == boardResultDto.getDto().getUserSeq()) {
			boardResultDto.getDto().setSameUser(true);
		}

		if (boardResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(boardResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(value = "/boards")
	public ResponseEntity<BoardResultDto> boardInsert(BoardDto boardDto, HttpServletRequest request) {

		boardDto.setUserSeq(((UserGetDto) request.getSession().getAttribute("userDto")).getUserSeq());
		BoardResultDto boardResultDto = service.boardInsert(boardDto);
		System.out.println("[POST]: " + boardResultDto);
		if (boardResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(boardResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(value = "/boards/{boardId}")
	public ResponseEntity<BoardResultDto> boardUpdate(BoardDto boardDto, HttpServletRequest request) {

		boardDto.setUserSeq(((UserGetDto) request.getSession().getAttribute("userDto")).getUserSeq());
		BoardResultDto boardResultDto = service.boardUpdate(boardDto);
		System.out.println("[PUT]: " + boardResultDto);
		if (boardResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(boardResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/boards/{boardId}")
	public ResponseEntity<BoardResultDto> boardDelete(@PathVariable(value = "boardId") int boardId) {

		BoardResultDto boardResultDto = service.boardDelete(boardId);
		System.out.println("[DELETE]: " + boardResultDto);

		if (boardResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(boardResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
