package com.mycom.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.trip.dao.BoardDao;
import com.mycom.trip.dto.BoardDto;
import com.mycom.trip.dto.BoardParamDto;
import com.mycom.trip.dto.BoardResultDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	@Transactional
	public BoardResultDto boardList(BoardParamDto boardParamDto) {

		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			List<BoardDto> list = dao.boardList(boardParamDto);
			int count = dao.boardListTotalCount();
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			BoardDto dto = dao.boardDetail(boardParamDto);
			boardResultDto.setDto(dto);
			boardResultDto.setResult(SUCCESS);

			if (dto.getReadCount() == 0) {
				dao.boardReadCountUpdate(boardParamDto.getBoardId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

	@Override
	public BoardResultDto boardInsert(BoardDto boardDto) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			dao.boardInsert(boardDto);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

	@Override
	public BoardResultDto boardUpdate(BoardDto boardDto) {

		BoardResultDto boardResultDto = new BoardResultDto();

		try {

			dao.boardUpdate(boardDto);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDelete(int boardId) {

		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			dao.boardDelete(boardId);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

}
