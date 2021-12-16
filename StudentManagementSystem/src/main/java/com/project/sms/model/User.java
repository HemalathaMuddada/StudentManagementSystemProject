package com.project.sms.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.sms.validation.PasswordMatches;
import com.project.sms.validation.ValidEmail;
import com.project.sms.validation.ValidPassword;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.var;


@Entity
@Table(name = "user_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_name" }) })
@EqualsAndHashCode(of = "id")
@Getter
@Setter

public class User implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3903243335716548729L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty(message="username must not be null")
	@Size(min = 4, message = "user name should have at least 4 characters")
	@Column(name="user_name")
	private String username;
	
	//@ValidPassword
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")
	@NotEmpty(message="firstname required")
	@NotNull
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty(message="lastname must not be null")
	@NotNull
	private String lastName;
	
	@ValidEmail
	@NotNull
	@NotEmpty(message="please provide email it should not be null")
	@Column(name="email",unique = true)
	private String email;
	
	@NotNull(message="please provide role")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	@OrderBy
	@JsonIgnore
	private List<Authority> authorities ;


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	

	
}