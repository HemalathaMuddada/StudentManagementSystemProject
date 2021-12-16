package com.project.sms.service;

import java.util.List;

import com.project.sms.dto.Orgnaizationdto;
import com.project.sms.model.College;
import com.project.sms.model.User;

public interface CollegeService {
	
	   public College create(Orgnaizationdto orgnaizationdto)  throws Exception;
		
		public College update(Orgnaizationdto orgnaizationdto);
		
			public void deleteById(Long id);
			
			public List<College> findColleges();
			
			public College findCollegeById(Long id);
			
		
}
