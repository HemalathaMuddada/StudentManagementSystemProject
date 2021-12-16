package com.project.sms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.dto.UserDto;
import com.project.sms.model.User;
import com.project.sms.service.UserDetailsServiceImpl;

@RequestMapping("/user")
@RestController
@CrossOrigin("http://localhost:4200")
public class AdminController {
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl; 


		@GetMapping
	    public List<User> getAll() {
	        return detailsServiceImpl.getAll();
	    }
		
		@GetMapping(value="/{id}")
		public ResponseEntity<User> getUserById(@PathVariable int id) {
			return ResponseEntity.ok().body(detailsServiceImpl.getUserById(id));
		}
		
		@PostMapping
	    @ResponseStatus(value = HttpStatus.OK)
	    public ResponseEntity<User> create(@Valid @RequestBody UserDto user) throws Exception {
	        detailsServiceImpl.create(user);
	        HttpHeaders headers = new HttpHeaders();
	        
	        return new ResponseEntity<>(headers, HttpStatus.FOUND).ok().build();
	           
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<User> update(@Valid @RequestBody UserDto users,@PathVariable int id) {
			users.setId(id);
			
			return ResponseEntity.ok().body(detailsServiceImpl.update(users));
			}
			

		
			@DeleteMapping("/{id}")
			public void deleteusers(@PathVariable ("id") int id) {
				detailsServiceImpl.delete(id);
			}
		
	        

}
