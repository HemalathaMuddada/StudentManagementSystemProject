package com.project.sms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.sms.dto.DepartmentDto;
import com.project.sms.model.College;
import com.project.sms.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query(value="select *from Department d where d.code in(:code)",nativeQuery = true)
List<Department> findByName(@Param("code") List<String>  code);

}
