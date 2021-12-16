package com.project.sms.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StudentDataDto {
		private Long id;
	private Long attendance;
	private String name;
	private String email;

	private UserDto userDto;
	private String username;
}
