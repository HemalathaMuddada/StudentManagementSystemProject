package com.project.sms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.dto.Orgnaizationdto;
import com.project.sms.dto.UserDto;
import com.project.sms.exceptions.ResourceNotFoundException;
import com.project.sms.mail.MailData;
import com.project.sms.model.Authority;
import com.project.sms.model.College;
import com.project.sms.model.User;
import com.project.sms.service.CollegeService;
import com.project.sms.service.CollegeServiceImpl;


@RestController
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	private CollegeServiceImpl service;
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
    public List<College>  getCompanies() {
       return this.service.findColleges(); 
    }
	
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<College> getUserById(@PathVariable long id) {
		return ResponseEntity.ok().body(this.service.findCollegeById(id));
	}
	
	
	
	@PostMapping
	   @ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<College> create(@RequestBody Orgnaizationdto orgnaizationdto)throws Exception {
		
		College company=this.service.create(orgnaizationdto);
		
		return new ResponseEntity<>( company,HttpStatus.CREATED);
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<College> update(@RequestBody Orgnaizationdto orgnaizationdto,@PathVariable Long id) {
		orgnaizationdto.setId(id);
		return ResponseEntity.ok().body(this.service.update(orgnaizationdto));
		
		}
	
	  @DeleteMapping(value="/{id}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public HttpStatus delete(@PathVariable Long id) {
			System.out.println("delete");
			this.service.deleteById(id);
			return HttpStatus.OK;
	        
	    }

	}
	

