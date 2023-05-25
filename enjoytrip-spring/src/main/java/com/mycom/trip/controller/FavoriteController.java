package com.mycom.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.trip.dto.favorite.FavoriteDto;
import com.mycom.trip.dto.favorite.FavoriteResultDto;
import com.mycom.trip.dto.favorite.HotplaceResultDto;
import com.mycom.trip.service.FavoriteService;

@RestController
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE,
		RequestMethod.PUT })
public class FavoriteController {

	@Autowired
	FavoriteService favoriteService;

	static final int SUCCESS = 1;
	static final int FAIL = -1;
	static final int NOT_EXIST = 0;

	@PostMapping(value = "/users/{userSeq}/places/favorites")
	public ResponseEntity<FavoriteResultDto> createFavorite(@RequestBody FavoriteDto favoriteDto) {
		System.out.println("post Favorite: " + favoriteDto);
		FavoriteResultDto favoriteResultDto = favoriteService.createFavorite(favoriteDto);

		if (favoriteResultDto.getResult() == SUCCESS) {
			System.out.println("post favorite Success");
			return ResponseEntity.ok().body(favoriteResultDto);
		} else {
			System.out.println("post Fail");
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(value = "/users/{userSeq}/places/favorites")
	public ResponseEntity<FavoriteResultDto> getFavoriteList(@PathVariable int userSeq) {

		FavoriteResultDto favoriteResultDto = favoriteService.getFavoriteList(userSeq);
		System.out.println("Favorits: " + favoriteResultDto);
		// Fail일때만 badRequest를 보냄, 그다음은 front층에서 result가 0이나 1이냐에 따라서 '좋아요한 이력이 없습니다'
		// 등으로 처리하도록 함
		if (favoriteResultDto.getResult() == FAIL)
			return ResponseEntity.badRequest().build();
		else
			return ResponseEntity.ok().body(favoriteResultDto);

	}

	@GetMapping(value = "/places/hotplaces")
	public ResponseEntity<HotplaceResultDto> getHotplace() {
		HotplaceResultDto hotplaceResultDto = favoriteService.getHotplaceList();

		// Fail일때만 badRequest를 보냄, 그다음은 front층에서 result가 0이나 1이냐에 따라서 '좋아요한 이력이 없습니다'
		// 등으로 처리하도록 함
		if (hotplaceResultDto.getResult() == SUCCESS)
			return ResponseEntity.ok().body(hotplaceResultDto);
		else
			return ResponseEntity.badRequest().build();

	}

	@GetMapping(value = "/places/hotplaces/{userSeq}")
	public ResponseEntity<HotplaceResultDto> getHotplaceFromUser(@PathVariable int userSeq) {

		System.out.println("hotplaceFromUser: " + userSeq);
		HotplaceResultDto hotplaceResultDto = favoriteService.getHotplaceListFromUser(userSeq);

		// Fail일때만 badRequest를 보냄, 그다음은 front층에서 result가 0이나 1이냐에 따라서 '좋아요한 이력이 없습니다'
		// 등으로 처리하도록 함
		if (hotplaceResultDto.getResult() == SUCCESS)
			return ResponseEntity.ok().body(hotplaceResultDto);
		else
			return ResponseEntity.badRequest().build();

	}
}
