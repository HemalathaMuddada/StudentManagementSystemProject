package com.project.sms.service;

import java.util.List;

import com.project.sms.dto.UserDto;
import com.project.sms.model.User;

public interface UserService {
	public List<User> getAll();
	public User create(UserDto user) throws Exception;
		
		public User update(UserDto user);
		
		 public void delete(int id);
		User getUserById(int id);

}
