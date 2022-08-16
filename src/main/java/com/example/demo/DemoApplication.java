package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class DemoApplication {

	static RestTemplate restTemplate = new RestTemplate();
	static String baseUrl = "http://94.198.50.185:7081/api/users";

	public static void main(String[] args) {

		useExchangeMethodsOfRestTemplate();
	}

	private static void useExchangeMethodsOfRestTemplate() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<List> responseEntity = getListUserByExchangeMethod(requestEntity);
		headers.set("Cookie", String.join(";", Objects.requireNonNull(responseEntity.getHeaders().get("Set-Cookie"))));

		User sysUser = new User();
		sysUser.setId(3L);
		sysUser.setName("James");
		sysUser.setLastName("Brown");
		sysUser.setAge((byte) 18);
		requestEntity = new HttpEntity<>(sysUser, headers);
		addUserByExchangeMethod(requestEntity);

		sysUser.setName("Thomas");
		sysUser.setLastName("Shelby");
		updateUserByExchangeMethod(requestEntity);

		deleteUserByExchangeMethod(requestEntity);
	}

	private static ResponseEntity<List> getListUserByExchangeMethod(HttpEntity<Object> requestEntity) {
		ResponseEntity<List> responseEntity = restTemplate.exchange(baseUrl,
				HttpMethod.GET,
				requestEntity,
				List.class);

		HttpHeaders responseHeaders = responseEntity.getHeaders();
		return responseEntity;
	}

	private static void addUserByExchangeMethod(HttpEntity<Object> requestEntity) {
		ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, String.class);

		String userDetails = responseEntity.getBody();
		HttpHeaders responseHeaders = responseEntity.getHeaders();
	}

	private static void updateUserByExchangeMethod(HttpEntity<Object> requestEntity) {
		ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestEntity, String.class);

		String userDetails = responseEntity.getBody();
		HttpHeaders responseHeaders = responseEntity.getHeaders();
	}

	private static void deleteUserByExchangeMethod(HttpEntity<Object> requestEntity) {
		ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl + "/3", HttpMethod.DELETE, requestEntity, String.class);

		String userDetails = responseEntity.getBody();
		HttpHeaders responseHeaders = responseEntity.getHeaders();
	}
}
