package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	static RestTemplate restTemplate = new RestTemplate();

	static String baseUrl = " http://94.198.50.185:7081/api/users";

	public static void main(String[] args) {

		//SpringApplication.run(DemoApplication.class, args);

		useExchangeMethodsOfRestTemplate();
	}


	private static void useExchangeMethodsOfRestTemplate() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

		getListUserByExchangeMethod(requestEntity);

		/*User sysUser = new User();
		sysUser.setFirstName("Arvind");
		sysUser.setLastName("Kuamr");
		sysUser.setAddress("Noida");
		sysUser.setGender("M");*/
		//requestEntity = new HttpEntity<>(sysUser, headers);

		//addUserByExchangeMethod(requestEntity);

		//updateUserByExchangeMethod(requestEntity);

		//deleteUserByExchangeMethod(requestEntity);
	}

	private static void getListUserByExchangeMethod(HttpEntity<Object> requestEntity) {
		ResponseEntity<List> responseEntity = restTemplate.exchange(baseUrl,
				HttpMethod.GET,
				requestEntity,
				List.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		System.out.println("status code - " + statusCode);
		List user = responseEntity.getBody();
		System.out.println("response body - " + user);
		HttpHeaders responseHeaders = responseEntity.getHeaders();
		System.out.println("response Headers - " + responseHeaders);
/*
        ResponseEntity<User> responseUser = restTemplate.exchange(baseUrl + "user/5",
                HttpMethod.GET,
                requestEntity,
                User.class);
        User userBody = responseUser.getBody();
        System.out.println("user object - " + userBody);*/
	}
}
