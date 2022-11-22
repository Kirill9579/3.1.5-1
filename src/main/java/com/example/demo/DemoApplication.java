package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class DemoApplication {
    static String url = "http://94.198.50.185:7081/api/users";
    static RestTemplate restTemplate = new RestTemplate();

    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity;
        User newUser = new User(3L, "James", "Brown", (byte) 2);

        String cookie = getAllUsers();
        headers.set("Cookie", cookie);

        entity = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> responseEntityPOST = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        result.append(responseEntityPOST.getBody());

        newUser.setName("Thomas");
        newUser.setLastName("Shelby");

        entity = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> responseEntityPUT = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        result.append(responseEntityPUT.getBody());

        entity = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> responseEntityDELETE = restTemplate.exchange(url + "/3", HttpMethod.DELETE, entity, String.class);
        result.append(responseEntityDELETE.getBody());

        System.out.println(result);
    }

    static String getAllUsers() {
        HttpEntity<String> res = new HttpEntity<>("");
        HttpEntity<String> responseGet = restTemplate.exchange(url, HttpMethod.GET, res, String.class);
        HttpHeaders headers = responseGet.getHeaders();
        String setCookie = headers.getFirst(HttpHeaders.SET_COOKIE);
        System.out.println(setCookie);
        return setCookie;
    }


}
