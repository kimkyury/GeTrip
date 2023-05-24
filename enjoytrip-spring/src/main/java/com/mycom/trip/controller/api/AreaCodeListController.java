package com.mycom.trip.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE,
		RequestMethod.PUT })
public class AreaCodeListController {

	private final String serviceId = "areaCode1";
	private final String serviceKey = "%2FicmQTs5MjuIFEvZGKIEXYvEk8TrlUIKAjQqVdKo9AYjjzNmHJmNb%2Bn1yM7QxiPHhqOI%2BDg7%2BwfOVyDkaRjvxg%3D%3D";
	private String serviceUrl = "";
	private String MobileOS = "ETC";
	private String MobileApp = "TestApp";
	private String _type = "json";
	private String numOfRows;
	private String pageNo;

	private String getServiceURL(String areaCode) {
		StringBuilder sb = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/");
		sb.append(serviceId).append("?serviceKey=").append(serviceKey).append("&numOfRows=").append(numOfRows)
				.append("&pageNo=").append(pageNo).append("&MobileOS=").append(MobileOS).append("&MobileOS=")
				.append(MobileOS).append("&MobileApp=").append(MobileApp).append("&_type=").append(_type)
				.append("&areaCode=").append(areaCode);
		return sb.toString();
	}

	@GetMapping("/trip/areaCode")
	public ResponseEntity<String> getAreaCode(
			@RequestParam(value = "numOfRows", required = false, defaultValue = "10") String numOfRows,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
			@RequestParam(value = "areaCode", required = false, defaultValue = "") String areaCode) throws IOException {

		this.serviceUrl = getServiceURL(areaCode);

		URL url = new URL(this.serviceUrl);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		BufferedReader br;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder result = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			result.append(line);
		}
		br.close();
		conn.disconnect();

		System.out.println(result.toString());

		return ResponseEntity.ok().body(result.toString());
	}
}
