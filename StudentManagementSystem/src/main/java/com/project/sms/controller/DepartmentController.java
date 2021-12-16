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

import com.project.sms.dto.DepartmentDto;
import com.project.sms.dto.Orgnaizationdto;
import com.project.sms.model.College;
import com.project.sms.model.Department;
import com.project.sms.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	   @ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Department> create(@RequestBody DepartmentDto departmentDto)throws Exception {
		
		Department department=this.departmentService.create(departmentDto);
		
		return new ResponseEntity<>( department,HttpStatus.OK);
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Department> update(@RequestBody DepartmentDto departmentDto,@PathVariable Long id) {
		departmentDto.setId(id);
		return ResponseEntity.ok().body(this.departmentService.update(departmentDto));
		
		}
	
	 @DeleteMapping(value="/{id}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public HttpStatus delete(@PathVariable Long id) {
			System.out.println("delete");
			this.departmentService.deleteById(id);
			return HttpStatus.OK;
	        
	    }
	
	 @GetMapping
		@ResponseStatus(value = HttpStatus.OK)
	    public List<Department>  getDepartments() {
	       return this.departmentService.finddepartments(); 
	    }
		
		
		
		@GetMapping(value="/{id}")
		public ResponseEntity<Department> getUserById(@PathVariable long id) {
			return ResponseEntity.ok().body(this.departmentService.findDepartmentById(id));
		}
		

}
