package com.project.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.sms.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	@Query(value = "SELECT * FROM Authority u where u.name IN (:authorities)", nativeQuery = true)
 // List<Authority> find(@Param("authorities") List<String> authorities);
	List<Authority> find(@Param("authorities")String authorities);
}