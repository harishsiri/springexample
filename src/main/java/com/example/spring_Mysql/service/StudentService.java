package com.example.spring_Mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.spring_Mysql.model.GroupSchool;
import com.example.spring_Mysql.model.Student;
import com.example.spring_Mysql.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> listAll() {
		return studentRepository.findAll();
	}

	public List<Student> getStudentAddress(String address) {
		return studentRepository.getAddress(address);
	}
	
	public long count(String schl_name) {
		return studentRepository.countName(schl_name);
	}
	
	//public List<Student> schoolGroup(String schl_name, String name ){
//		return studentRepository.getSchoolGroup(schl_name, name);
	//}
	
	public List<GroupSchool> group(){
		return studentRepository.groupSchool();
	}
	
	//JPQL Sorting
	public List<Student> sortingName() {
		return studentRepository.sortByName();
	}
	
	//NATIVE QUERY
	public List<Student> getStudentSchool(String schl_name) {
		return studentRepository.getSchoolName(schl_name);
	}
	
	//JPQL DELETE
	public void deleteStudent(String name) {
		studentRepository.deleteByName(name);
	}
	
	//JPQL UPDATE
	public void addressUpdate(String address, String name) {
		studentRepository.updateStudentInfo(address, name);
	}
	
	public void save(Student student) {
		studentRepository.save(student);
	}
	

	public Student get(Integer id) {
		return studentRepository.findById(id).get();

	}

	public void delete(Integer id) {
		studentRepository.deleteById(id);
	}
}
