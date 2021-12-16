package com.project.sms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.sms.validation.ValidEmail;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "StudentData",uniqueConstraints = { @UniqueConstraint(columnNames = { "parent_email_id" }) })
@Getter
@Setter
public class StudentData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
    @Column(name = "attendance_in_percentage")
    private Long attendance;
    
    @Column(name="parent_name")
    @NotNull(message="mandatory field")
    private String name;
    
    @ValidEmail
    @Column(name = "parent_email_id")
    @NotNull(message="mandatory field")
    private String parent_email_id;
    
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User hoduser;

    @OneToOne
    @JoinColumn(name="student_id",referencedColumnName = "id")
    @JsonIgnore
    private User studentuser;


}
