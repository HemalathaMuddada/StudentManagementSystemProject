package com.project.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.sms.model.College;
import com.project.sms.model.Department;
import com.project.sms.model.StudentData;
import com.project.sms.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT DISTINCT user FROM User user " +
            "INNER JOIN FETCH user.authorities AS authorities " +
            "WHERE user.username = :username")
   public User findByUsername(@Param("username") String username);
   

	public void save(College c);
	public void save(Department d);

	public void save(StudentData studentData);
}
