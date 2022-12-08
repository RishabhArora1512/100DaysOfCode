package com.coderazor.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	
	private UserDaoService service;
	
	@Autowired
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/getUsers")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retriveAllUsers(@PathVariable int id) throws Exception {
		User findOne  = service.findOne(id);
		if(findOne == null) { throw new Exception();} 
		return findOne;
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = service.saveUserResource(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId())
				.toUri(); // get location of newly created user in headers
		
		return ResponseEntity.created(location).build();//created - 201 Response status
	}
}
