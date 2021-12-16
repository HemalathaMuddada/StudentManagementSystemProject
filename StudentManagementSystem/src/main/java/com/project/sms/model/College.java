package com.project.sms.model;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "college",uniqueConstraints = { @UniqueConstraint(columnNames = { "college_code" }) })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "college_name")
    @NotNull(message="It should be not null")
    private String name;
    
    @Column(name = "college_code")
    private String code;
    
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "college_department", 
	joinColumns = @JoinColumn(name = "college_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
    @OrderBy
    @JsonIgnore
    private List<Department> department;
    
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private User user;
    
    
    
    

}
