package com.project.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.sms.dto.DepartmentDto;
import com.project.sms.dto.UserDto;
import com.project.sms.exceptions.ResourceNotFoundException;
import com.project.sms.mail.EmailService;
import com.project.sms.mail.MailData;
import com.project.sms.model.Authority;
import com.project.sms.model.College;
import com.project.sms.model.Department;
import com.project.sms.model.PasswordGenerator;
import com.project.sms.model.User;
import com.project.sms.repository.AuthorityRepository;
import com.project.sms.repository.DepartmentRepository;
import com.project.sms.repository.UserRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
@Autowired
private DepartmentRepository departmentRepository;
@Autowired
private UserRepository repository;
@Autowired
private AuthorityRepository authorityRepository;
@Autowired
private PasswordGenerator passwordGenerator;

@Autowired
private EmailService emailService;

	@Override
	@Transactional
	public Department create(DepartmentDto departmentDto)throws Exception {
		
		Department department=new Department();
		department.setName(departmentDto.getName());
		department.setCode(departmentDto.getCode());
	
		
		UserDto user=departmentDto.getUserDto();
		
		User user1 = new User();
        user1.setEmail(user.getEmailId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
        String pass=passwordGenerator.generateRandomPassword(8);
        String encodedPassword = passwordEncoder.encode(pass);
        System.out.println(pass);
        
        user1.setPassword(encodedPassword);
        User u= null;
  		User uu=repository.findByUsername(departmentDto.getCusername());
  		department.setUser(uu);
                                                                                                                                                                                        
//Validation for principal role
        User user2=null;
        List<Authority> role=authorityRepository.findAll();
        String name=role.get(1).getName();
       
        List<String> n=new ArrayList<String>();
        n.add(name);

  		
        List<Authority> listAuList=authorityRepository.findAll();
        List<Authority> addList=authorityRepository.find(user.getRole().toUpperCase());
       for(int i=0;i<listAuList.size();i++)
       { 
      	 
      	if(user.getRole().equalsIgnoreCase(listAuList.get(i).getAuthority()))
      	 {
      		 System.out.println("if manin "+user.getRole());
      		
      		 if(user.getRole().equalsIgnoreCase(listAuList.get(1).getAuthority()))
      			{
      		
      			 throw new ResourceNotFoundException("u cant add");
      		 }
      		 
      		 else if(listAuList.get(0).getAuthority()!=user.getRole()&&user.getRole().equals(listAuList.get(2).getAuthority())){
      			
          		 user1.setAuthorities(addList);
          			user2= repository.save(user1);
          			System.out.println("save");
          			
      		 }

      	 }
      	 }
        
       // user1.setAuthorities(addAuthorities);
          
			  MailData mail = new MailData();
					 mail.setSubject("Welcome to Student Management System Program");
			  mail.setToEmail(user.getEmailId());
			  mail.setContent("You were added by "+n+"\n" +"Username :"+user.getUsername() +"\n"+ "password :"+pass);
			  emailService.sendEmail(mail);

		  		/*Object users = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		  		if (users instanceof UserDetails) {
		  		  String username = ((UserDetails)users).getUsername();
		  		  u=this.repository.findByUsername(username);
		  		  department.setUser(u);
		  		} else {
		  		  String username = users.toString();
		  		  
		  	}*/
			  		
			  		department.setHoduser(user2);
			  		return departmentRepository.save(department);
			  	}
			  
		
	
	@Override
	public Department update(DepartmentDto departmentDto) {
		  Optional<Department> departmentdb=this.departmentRepository.findById(departmentDto.getDeptid());
			

			
			if(departmentdb.isPresent()) {
				Department d=departmentdb.get();
				d.setName(departmentDto.getName());
				d.setCode(departmentDto.getCode());
		
				 repository.save(d);
		          return d;
			}
			else {
				throw new ResourceNotFoundException("Record not found with id" + departmentdb.get());
			}
	}

	@Override
	public void deleteById(Long id) {
Optional<Department> department=departmentRepository.findById(id);
if(department.isPresent()) {
	this.departmentRepository.deleteById(id);
}
else
{
	throw new ResourceNotFoundException("record not found with this id:"+id);
}
		
	}

	@Override
	public List<Department> finddepartments() {
		
		return this.departmentRepository.findAll();
	}

	@Override
	public Department findDepartmentById(Long id) {
		Optional<Department> departmentdb=this.departmentRepository.findById(id);
		if(departmentdb.isPresent()) {
			 
			return departmentdb.get();
		}
		
		else {
			throw  new ResourceNotFoundException("Record not found with id  :" +id);
		}
	}
	
	

}
