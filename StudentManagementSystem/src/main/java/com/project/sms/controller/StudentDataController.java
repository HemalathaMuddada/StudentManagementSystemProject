package com.project.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.project.sms.dto.StudentDataDto;
import com.project.sms.model.StudentData;
import com.project.sms.service.StudentDataService;

@RequestMapping("/studentdata")
@RestController
public class StudentDataController {

	@Autowired
	private StudentDataService studentDataService;
	
	@PostMapping
	   @ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<StudentData> create(@RequestBody StudentDataDto studentDataDto)throws Exception {
		
		StudentData studentData=this.studentDataService.create(studentDataDto);
		
		return new ResponseEntity<>(studentData,HttpStatus.CREATED);
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<StudentData> update(@RequestBody StudentDataDto studentDataDto,@PathVariable Long id) {
		studentDataDto.setId(id);
		return ResponseEntity.ok().body(this.studentDataService.update(studentDataDto));
		
		}
	
	 @DeleteMapping(value="/{id}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public HttpStatus delete(@PathVariable Long id) {
			System.out.println("delete");
			this.studentDataService.deleteById(id);
			return HttpStatus.OK;
	        
	    }
	
	 @GetMapping
		@ResponseStatus(value = HttpStatus.OK)
	    public List<StudentData>  getStudentDatas() {
	       return this.studentDataService.findStudentDatas();
	    }
		
		
		
		@GetMapping(value="/get/{id}")
		public ResponseEntity<StudentData> getStudentDataById(@PathVariable long id) {
			return ResponseEntity.ok().body(this.studentDataService.findStudentDataById(id));
		}
		
	

}
