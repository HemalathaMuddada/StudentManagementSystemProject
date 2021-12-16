package com.project.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sms.dto.UserDto;
import com.project.sms.exceptions.ResourceNotFoundException;
import com.project.sms.exceptions.UserAlreadyExistException;
import com.project.sms.exceptions.UserNotFoundException;
import com.project.sms.mail.EmailService;
import com.project.sms.mail.MailData;
import com.project.sms.model.Authority;
import com.project.sms.model.PasswordGenerator;
//import com.project.sms.email.UserCreatedEvent;

import com.project.sms.model.User;
import com.project.sms.repository.AuthorityRepository;
import com.project.sms.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
  @Autowired
  private AuthorityRepository authorityRepository;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	@Autowired
	private EmailService emailService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =repository.findByUsername(username);

		if (user != null) {
			return user;
		}
		
		
		throw new UsernameNotFoundException("username not found:"+username);
	}
	
	

	@Override
    @Transactional(readOnly = true)
	public List<User> getAll() {

		return repository.findAll();
	}

	@Override
	@Transactional
	public User create(UserDto user)throws Exception {
	        User userWithDuplicateUsername = repository.findByUsername(user.getUsername());
	        if(userWithDuplicateUsername != null && user.getId() != userWithDuplicateUsername.getId()) {
	          
	            throw new UserAlreadyExistException("Duplicate username.");
	        }
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
	  		
	  		Object users = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	  		if (users instanceof UserDetails) {
	  		  String username = ((UserDetails)users).getUsername();
	  		  u=this.repository.findByUsername(username);
	  		  //user1.setUsername(u);;
	  		} else {
	  		  String username = users.toString();
	  	}
	  		
	        
//Validation for super admin role
	        User user2=null;
	        List<Authority> role=authorityRepository.findAll();
	        String name=role.get(0).getName();
	       
	        List<String> n=new ArrayList<String>();
	        n.add(name);

	  		
	        List<Authority> listAuList=authorityRepository.findAll();
	        List<Authority> addList=authorityRepository.find(user.getRole().toUpperCase());
	       for(int i=0;i<listAuList.size();i++)
	       { 
	      	 
	      	if(user.getRole().equalsIgnoreCase(listAuList.get(i).getAuthority()))
	      	 {
	      		 System.out.println("if manin "+user.getRole());
	      		
	      		 if(user.getRole().equalsIgnoreCase(listAuList.get(0).getAuthority()))
	      			{
	      			
	      			 System.out.println(listAuList.get(i).getAuthority()+"inner if");
	      			 throw new ResourceNotFoundException("u cant add");
	      		 }
	      		 
	      		 else if(listAuList.get(0).getAuthority()!=user.getRole()&&user.getRole().equals(listAuList.get(1).getAuthority())){
	      			
	          		 user1.setAuthorities(addList);
	          			user2= repository.save(user1);
	          			System.out.println("save");
	          			
	      		 }
	
	      	 }
	      	 }
	        	 MailData mail = new MailData();
				 mail.setSubject("Welcome to Student Management System Program");
		  mail.setToEmail(user.getEmailId());
		  mail.setContent("You were added by "+" : "+user.getUsername()+"("+user.getRole()+")"+"\n" +"Username :"+user.getUsername() +"\n"+ "password :"+pass);
		  emailService.sendEmail(mail);
		  return user2;
	        }
	
	
	public UserDetailsServiceImpl() {
		super();
	}


	@Override
	@Transactional
	public User update(UserDto user) {
		
Optional<User> users=this.repository.findById(user.getId());
		
		if(users.isPresent()) {
			User u=users.get();
			u.setId(user.getId());
			u.setUsername(user.getUsername());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setEmail(user.getEmailId());
		    u.setPassword(new BCryptPasswordEncoder(8).encode(user.getPassword()));
		    MailData mail = new MailData();
			 mail.setSubject("Welcome to Student Management System Program");
	  mail.setToEmail(user.getEmailId());
	  mail.setContent("Your details were updated successfully.... "+" : "+"("+user.getRole()+")"+"\n" +"Username :"+user.getUsername() +"\n"+ "password :"+user.getPassword());
	  try {
	  emailService.sendEmail(mail);
	  }
	  catch(Exception e) {
		  
	  }
		  //  repository.save(u);
		  
	  repository.save(u);
	  
		    return u;
		}
		else {
			throw new UserNotFoundException("user not found with id" + user.getId());
		}
		

		
	}
	@Override
	@Transactional
	public void delete(int id) {
Optional<User> users=this.repository.findById(id);
		
		if(users.isPresent()) {
			
			this.repository.deleteById(id);
		}
		else {
			throw new ResourceNotFoundException("Record not found with id  :" +id);
		}
	
	}
	
	@Override
	@Transactional
	public User getUserById(int id) {
		Optional<User> users=this.repository.findById(id);
		if(users.isPresent()) {
			return users.get();
		}
		
		else {
			throw  new ResourceNotFoundException("Record not found with id  :" +id);
		}
		
	}
}
