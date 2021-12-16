package com.project.sms.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DepartmentDto {
	private Long id;
private String name;
private String code;

private UserDto userDto;
private String username;
}
