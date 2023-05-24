package com.mycom.trip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.trip.dto.TripDto;
import com.mycom.trip.dto.TripParamDto;

@Mapper
public interface TripDao {
	List<TripDto> getSidoCode();
	List<TripDto> getGugunCode(int sidoCode);
	List<TripDto> getTripList(TripParamDto tripParamDto);
	TripDto getDetailTrip(int contentId);
}
