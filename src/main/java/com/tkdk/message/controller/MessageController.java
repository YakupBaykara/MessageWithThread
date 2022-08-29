package com.tkdk.message.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tkdk.message.entity.Message;
import com.tkdk.message.service.MessageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/message")
@AllArgsConstructor
public class MessageController {
	
	private final MessageService messageService;

	@PostMapping
	public void saveAndSendMessage(@RequestBody Message message) {
		messageService.saveMessage(message);
		messageService.sendMessage(message); 
	}
	
	@GetMapping("/stop")
	public void stopRequest() {
		messageService.stopRequest();
	}
	
	@GetMapping("/resume")
	public void resumeRequest() {

	}

}
