package com.example.demo.controller;

import com.example.demo.model.ChatRequestResponse;
import com.example.demo.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;


    @PostMapping
    public ChatRequestResponse chat(@RequestBody ChatRequestResponse request) {
        return new ChatRequestResponse(chatBotService.getResponse(request.getMessage()));
    }

    @PostMapping("/b")
    public ChatRequestResponse chat1(@RequestBody ChatRequestResponse request) {
        return new ChatRequestResponse(Base64.getEncoder().encodeToString(request.getMessage().getBytes(StandardCharsets.UTF_8)));
    }
}
