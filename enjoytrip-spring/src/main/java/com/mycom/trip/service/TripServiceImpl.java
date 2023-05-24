package com.mycom.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.trip.dao.TripDao;
import com.mycom.trip.dto.TripDto;
import com.mycom.trip.dto.TripParamDto;
import com.mycom.trip.dto.TripResultDto;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	TripDao dao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public TripResultDto getSidoCode() {
		TripResultDto tripResultDto = new TripResultDto();
		try {
			List<TripDto> dto = dao.getSidoCode();
			tripResultDto.setList(dto);
			tripResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			tripResultDto.setResult(FAIL);
		}
		return tripResultDto;
	}

	@Override
	public TripResultDto getGugunCode(int sidoCode) {
		TripResultDto tripResultDto = new TripResultDto();
		try {
			List<TripDto> dto = dao.getGugunCode(sidoCode);
			tripResultDto.setList(dto);
			tripResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			tripResultDto.setResult(FAIL);
		}
		return tripResultDto;
	}

	@Override
	public TripResultDto getTripList(TripParamDto tripParamDto) {
		TripResultDto tripResultDto = new TripResultDto();
		try {
			List<TripDto> list = dao.getTripList(tripParamDto);
			tripResultDto.setList(list);
			tripResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			tripResultDto.setResult(FAIL);
		}
		return tripResultDto;
	}

	@Override
	public TripResultDto getDetailTrip(int contentId) {
		TripResultDto tripResultDto = new TripResultDto();
		try {
			TripDto dto = dao.getDetailTrip(contentId);
			tripResultDto.setDto(dto);
			tripResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			tripResultDto.setResult(FAIL);
		}
		return tripResultDto;
	}

}
