package com.mycom.trip.service;

import com.mycom.trip.dto.TripParamDto;
import com.mycom.trip.dto.TripResultDto;

public interface TripService {
	TripResultDto getSidoCode();
	TripResultDto getGugunCode(int sidoCode);
	TripResultDto getTripList(TripParamDto tripParamDto);
	TripResultDto getDetailTrip(int contentId);
}
