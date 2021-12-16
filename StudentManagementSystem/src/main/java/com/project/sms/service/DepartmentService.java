package com.project.sms.service;

import java.util.List;

import com.project.sms.dto.DepartmentDto;

import com.project.sms.model.Department;

public interface DepartmentService {
	
 public Department create(DepartmentDto departmentDto)throws Exception;
	
	public Department update(DepartmentDto departmentDto);
	
		public void deleteById(Long id);
		
		public List<Department> finddepartments();
		
		public Department findDepartmentById(Long id);
		

}
