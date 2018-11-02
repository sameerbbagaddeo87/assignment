package com.uxpsystems.assignement.assignement.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uxpsystems.assignement.assignement.common.ResponseWrapper;
import com.uxpsystems.assignement.assignement.dao.User;
import com.uxpsystems.assignement.assignement.service.UserService;

@RestController
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@PostMapping(path="/user")
	public ResponseEntity<Object> saveUser(@RequestBody String body) throws JsonParseException, JsonMappingException, IOException{
		
		LOGGER.info("saveSuer API execution starts");
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(body, User.class);
		Long userId = userService.createUser(user);
		
		Map<String,Long> responseData = new HashMap<String,Long>();
		responseData.put("userId", userId);
		LOGGER.info("saveSuer API execution completed");
		return new ResponseEntity<>(new ResponseWrapper("User created", responseData),HttpStatus.CREATED);
	}
	
	@PutMapping(path="/user/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable Long userId,@RequestBody String body) throws JsonParseException, JsonMappingException, IOException{
		LOGGER.info("updateUuer API execution starts");
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(body, User.class);
		user.setId(userId);
		userService.updateUser(user);
		Map<String,Long> responseData = new HashMap<String,Long>();
		responseData.put("userId", userId);
		LOGGER.info("updateUuer API execution completed");
		return new ResponseEntity<>(new ResponseWrapper("User updated", responseData),HttpStatus.OK);
		
	}
	
	@DeleteMapping(path="/user/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable String userId){
		LOGGER.info("deleteUser API execution starts");
		Long Id = userService.deleteUser(Long.valueOf(userId));
		Map<String,Long> responseData = new HashMap<String,Long>();
		responseData.put("userId", Long.valueOf(userId));
		LOGGER.info("deleteUuer API execution ends");
		return new ResponseEntity<>(new ResponseWrapper("User deleted", responseData),HttpStatus.OK);
		
	}
	
	@GetMapping(path="/user/{userId}")
	public ResponseEntity<Object> getUser(@PathVariable String userId){
		LOGGER.info("getUser API execution starts");
		User user = userService.getUser(Long.valueOf(userId));
		LOGGER.info("getUser API execution ends");
		return new ResponseEntity<>(new ResponseWrapper("User found", user),HttpStatus.OK);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseWrapper> handleException(Exception ex){
		String errorMsg = "";
		HttpStatus status = null;
		if(ex instanceof JsonParseException || ex instanceof JsonMappingException ||  ex instanceof IOException){
			errorMsg = "Invalid json passed";
			status = HttpStatus.BAD_REQUEST;
		}else if(ex instanceof NoSuchElementException){
			errorMsg = "User not found";
			status = HttpStatus.NOT_FOUND;
		}else{
			errorMsg = "Generic error occured";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(new ResponseWrapper(errorMsg, null),status);
	}
}
