package com.project.sms.dto;




import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orgnaizationdto {
	

	private Long id;
	private String college_name;
	private String college_code;

	private List<String> name;
	
	private String username ;
	
	
}

