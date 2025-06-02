package com.example.demo.service;

import com.example.demo.model.JWTTokenRequest;
import com.example.demo.model.JWTTokenResponse;
import com.example.demo.model.QueryRequest;
import com.example.demo.model.QueryResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;


@Service
public class ChatBotService {

    @Value("${u}")
    private String username;

    @Value("${p}")
    private String password;
    @Value("${jwturl}")
    private  String jwturl;
    @Value("${messageurl}")
    private  String messageUrl;

    private final RestClient restClient;

    public ChatBotService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.example.com") // Replace with actual endpoint
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }



    public String getResponse(String request) {
        
        if (StringUtils.isBlank(request)){
            return "";
        }



        JWTTokenResponse jwtResponse=  restClient.post()
                .uri(jwturl)
                .body(new JWTTokenRequest(username,  new String((Base64.getDecoder().decode(password.getBytes(StandardCharsets.UTF_8))),StandardCharsets.UTF_8)))
                .retrieve()
                .body(JWTTokenResponse.class);

        QueryResponse queryResponse= restClient.post()
                .uri(messageUrl)
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("apiToken",jwtResponse.getToken())
                .body(new QueryRequest(List.of(request),"en"))
                .retrieve()
                .body(QueryResponse.class);




        return queryResponse.getResult().getSpeech();
    }




}
