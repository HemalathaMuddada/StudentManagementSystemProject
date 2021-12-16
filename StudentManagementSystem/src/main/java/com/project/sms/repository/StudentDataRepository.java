package com.project.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.sms.model.Department;
import com.project.sms.model.StudentData;

@Repository
public interface StudentDataRepository extends JpaRepository<StudentData, Long> {
	
}
