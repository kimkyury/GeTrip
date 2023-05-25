package com.mycom.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.trip.dto.TripParamDto;
import com.mycom.trip.dto.TripResultDto;
import com.mycom.trip.service.TripService;

@RestController
@CrossOrigin(origins = "http://192.168.203.102:8080", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE,
		RequestMethod.PUT })
public class TripController {
	private static final long serialVersionUID = 1L;

	@Autowired
	TripService tripService;

	private static final int SUCCESS = 1;

	@GetMapping("/codes")
	public ResponseEntity<TripResultDto> getSidoCode() {
		TripResultDto tripResultDto = tripService.getSidoCode();

		if (tripResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(tripResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/codes/{gugunCode}")
	public ResponseEntity<TripResultDto> getGugunCode(@PathVariable int gugunCode) {
		TripResultDto tripResultDto = tripService.getGugunCode(gugunCode);

		if (tripResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(tripResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/trips")
	public ResponseEntity<TripResultDto> getTripList(
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "sidoCode", required = false, defaultValue = "0") int sidoCode,
			@RequestParam(value = "gugunCode", required = false, defaultValue = "0") int gugunCode,
			@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord) {
		TripParamDto tripParamDto = new TripParamDto();
		tripParamDto.setOffset(offset);
		tripParamDto.setLimit(limit);
		tripParamDto.setSidoCode(sidoCode);
		tripParamDto.setGugunCode(gugunCode);
		tripParamDto.setSearchWord(searchWord);
		// 프론트에서 json으로 불러주지 않으면 아래의 코드 실행해야함.
//		tripParamDto.setLimit(10);
//		tripParamDto.setOffset(0);
//		if(tripParamDto.getSearchWord() == null)tripParamDto.setSearchWord("");

		TripResultDto tripResultDto = tripService.getTripList(tripParamDto);
		if (tripResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(tripResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/trips/{contentId}")
	public ResponseEntity<TripResultDto> getDetailTrip(@PathVariable int contentId) {
		TripResultDto tripResultDto = tripService.getDetailTrip(contentId);
		if (tripResultDto.getResult() == SUCCESS) {
			return ResponseEntity.ok().body(tripResultDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
