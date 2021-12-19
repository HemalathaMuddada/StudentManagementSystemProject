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

import com.project.sms.dto.StudentDataDto;
import com.project.sms.dto.UserDto;
import com.project.sms.exceptions.ResourceNotFoundException;
import com.project.sms.mail.EmailService;
import com.project.sms.mail.MailData;
import com.project.sms.model.Authority;
import com.project.sms.model.Department;
import com.project.sms.model.PasswordGenerator;
import com.project.sms.model.StudentData;
import com.project.sms.model.User;
import com.project.sms.repository.AuthorityRepository;
import com.project.sms.repository.StudentDataRepository;
import com.project.sms.repository.UserRepository;

@Service
public class StudentDataServiceImpl implements StudentDataService{
	@Autowired
	private StudentDataRepository dataRepository;
	@Autowired
	private UserRepository repository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private PasswordGenerator passwordGenerator;
	@Autowired
	private EmailService emailService;
	  MailData mail = new MailData();
	 
	
	@Override
	@Transactional
	public StudentData create(StudentDataDto studentDataDto) throws Exception {
		StudentData studentData=new StudentData();
		studentData.setAttendance(studentDataDto.getAttendance());
		studentData.setName(studentDataDto.getName());
		studentData.setParent_email_id(studentDataDto.getEmail());
		
		
UserDto user=studentDataDto.getUserDto();
		
User user1 = new User();
        user1.setEmail(user.getEmailId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
        String pass=passwordGenerator.generateRandomPassword(8);
        String encodedPassword = passwordEncoder.encode(pass);
        System.out.println(pass);
        
              
            
  		User uu=repository.findByUsername(studentDataDto.getCusername());
  		studentData.setHoduser(uu);
  		
//Validation for HOD ROLE
        User user2=null;
        List<Authority> role=authorityRepository.findAll();
        String name=role.get(2).getName();
       
        List<String> n=new ArrayList<String>();
        n.add(name);

  		
        List<Authority> listAuList=authorityRepository.findAll();
        List<Authority> addList=authorityRepository.find(user.getRole().toUpperCase());
       for(int i=0;i<listAuList.size();i++)
       { 
      	 
      	if(user.getRole().equalsIgnoreCase(listAuList.get(i).getAuthority()))
      	 {
      		 System.out.println("if manin "+user.getRole());
      		
      		 if(user.getRole().equalsIgnoreCase(listAuList.get(2).getAuthority()))
      			{
      		
      			 throw new ResourceNotFoundException("u cant add");
      		 }
      		 
      		 else if(listAuList.get(2).getAuthority()!=user.getRole()&&user.getRole().equals(listAuList.get(3).getAuthority())){
      			
          		 user1.setAuthorities(addList);
          			user2= repository.save(user1);
          			System.out.println("save");
          			
      		 }

      	 }
      	 }

/*User u= null;
		
		Object users = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (users instanceof UserDetails) {
		  String username = ((UserDetails)users).getUsername();
		  u=this.repository.findByUsername(username);
		  studentData.setHoduser(u);
		} else {
		  String username = users.toString();
	}*/
      
		 mail.setSubject("Welcome to Student Management System Program");
			  mail.setToEmail(user.getEmailId());
			//  mail.setContent("You were added by "+u.getUsername()+" : "+"("+n+")"+"\n" +"Username :"+user.getUsername() +"\n"+ "password :"+pass);
			  mail.setContent("You were added by "+" : "+"("+n+")"+"\n" +"Username :"+user.getUsername() +"\n"+ "password :"+pass);
			  emailService.sendEmail(mail);
			  
			 if(studentDataDto.getAttendance()<65) {
				 mail.setSubject("Attendance Alert!!!!");
		  mail.setToEmail(studentDataDto.getEmail());
		  mail.setContent("Your ward "+" "+user1.getFirstName()+" "+user1.getLastName()+" "+"having less attendane with a percentage of"+" "+studentData.getAttendance());
		  emailService.sendEmail(mail);
			 }
			 else {
				 System.out.println("Everything Ok");
			 }
	
		studentData.setStudentuser(user2);
		return dataRepository.save(studentData);
	}

	@Override
	public StudentData update(StudentDataDto studentDataDto) {
		
		  Optional<StudentData> studentdb=this.dataRepository.findById(studentDataDto.getId());
			

			if(studentdb.isPresent()) {
				StudentData studentData=studentdb.get();
				studentData.setAttendance(studentDataDto.getAttendance());
				studentData.setParent_email_id(studentDataDto.getEmail());
			

				 repository.save(studentData);
		          return studentData;
			}
			else {
				throw new ResourceNotFoundException("Record not found with id" + studentdb.get());
			}
	
	}
	@Override
	public void deleteById(Long id) {
		Optional<StudentData> department=dataRepository.findById(id);
		if(department.isPresent()) {
			this.dataRepository.deleteById(id);
		}
		else
		{
			throw new ResourceNotFoundException("record not found with this id:"+id);
		}
				
			}

	@Override
	public List<StudentData> findStudentDatas() {
		return this.dataRepository.findAll();
	}

	@Override
	public StudentData findStudentDataById(Long id) {
		Optional<StudentData> studentdb=this.dataRepository.findById(id);
		if(studentdb.isPresent()) {
			return studentdb.get();
		}
		
		else {
			throw  new ResourceNotFoundException("Record not found with id  :" +id);
		}

}
}
