package com.project.sms.dto;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@ToString

public class UserDto {
	private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @NotNull
    @NotEmpty
    private String emailId;
    private String role;
    
    private Orgnaizationdto orgnaizationdto;
    private DepartmentDto departmentDto;
	
	
   // private boolean isPresent;

   
}