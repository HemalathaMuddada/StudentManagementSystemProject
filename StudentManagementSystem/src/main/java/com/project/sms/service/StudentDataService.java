package com.project.sms.service;

import java.util.List;

import com.project.sms.dto.StudentDataDto;
import com.project.sms.model.StudentData;


public interface StudentDataService {
	public StudentData create(StudentDataDto studentDataDto)  throws Exception;
	
	public StudentData update(StudentDataDto studentDataDto);
	
		public void deleteById(Long id);
		
		public List<StudentData> findStudentDatas();
		
		public StudentData findStudentDataById(Long id);
}
