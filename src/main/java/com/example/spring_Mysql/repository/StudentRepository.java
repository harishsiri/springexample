package com.example.spring_Mysql.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring_Mysql.model.GroupSchool;
import com.example.spring_Mysql.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	// JPQL
	@Query("SELECT s from student s where s.address=?1")
	public List<Student> getAddress(String address);

	@Query("SELECT s from student s order by s.name ASC")
	public List<Student> sortByName();
	
	@Transactional
	@Modifying
	@Query("DELETE student s WHERE s.name=?1")
	public void deleteByName(String name);
	
	@Transactional
	@Modifying
	@Query("update student s set s.address = ?1 where s.name in ?2")
	int updateStudentInfo(String address,  String name);
	
	@Query("Select COUNT(*) as SchoolCount FROM student s  where s.schl_name=?1")
	long countName(String schl_name);
	

	@Query(value= "SELECT COUNT(*) as count, schl_name as schlname"
			+ " FROM student"
			+ " GROUP BY schl_name", nativeQuery= true)
	public List<GroupSchool> groupSchool();
	// Native Query
	


	@Query(value = "SELECT * FROM student s where s.schl_name=?1", nativeQuery = true)
	public List<Student> getSchoolName(String schl_name);

}
