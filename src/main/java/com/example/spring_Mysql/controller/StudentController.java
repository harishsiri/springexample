package com.example.spring_Mysql.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_Mysql.model.GroupSchool;
import com.example.spring_Mysql.model.Student;
import com.example.spring_Mysql.service.StudentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	public StudentService service;

	@GetMapping("/getAll")
	@ApiOperation(value = "Get All the Student Deatails", response = Student.class)
	public List<Student> listAll() {
		return service.listAll();
	}

	@PostMapping("/add")
	@ApiOperation(value = "Add Student Deatails by Id", response = Student.class)
	public String add(@RequestBody Student student) {
		service.save(student);
		return "New Student Added";
	}

	@GetMapping("/id/{id}")

	@ApiOperation(value = "Find Student by Id", response = Student.class)
	public ResponseEntity<Student> get(@PathVariable Integer id) {
		try {
			Student student = service.get(id);
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{address}")
	@ApiOperation(value = "Find Student by Address", response = Student.class)
	public ResponseEntity<List<Student>> getStudent(@PathVariable String address) {
		try {

			return new ResponseEntity<List<Student>>(service.getStudentAddress(address), HttpStatus.OK);
		} catch (NoSuchElementException e) {

			return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
		}
	}

	// NATIVE QUERY
	@GetMapping("/schl/{schl_name}")
	@ApiOperation(value = "Find Student form School Name", response = Student.class)
	public ResponseEntity<List<Student>> getStudentSchool(@PathVariable String schl_name) {
		try {

			return new ResponseEntity<List<Student>>(service.getStudentSchool(schl_name), HttpStatus.OK);
		} catch (NoSuchElementException e) {

			return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
		}
	}

//	@GetMapping("/group")
//	public ResponseEntity<List<Student>> getgroupschool(@PathVariable String schl_name, @PathVariable String name){
//		try {
//			return new ResponseEntity<List<Student>>(service.schoolGroup(schl_name, name));
//		}
//	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update Student Deatails by Id", response = Student.class)
	public ResponseEntity<Student> update(@RequestBody Student student, @PathVariable Integer id) {
		try {
			Student existingStudent = service.get(id);
			service.save(student);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}

	// Count
	@GetMapping("/count/{schl_name}")
	@ApiOperation(value = "Count the Students with School Name", response = Student.class)
	public long counting(@PathVariable String schl_name) {
		return service.count(schl_name);
	}
	
	//GROUP
	@GetMapping("/group")
	public List<GroupSchool> groupS() {
		return service.group();
	}

	// JPQL SORTING
	@GetMapping("/sort")
	@ApiOperation(value = "Get All the Student Deatails by Name Sorting", response = Student.class)
	public List<Student> sort() {
		return service.sortingName();
	}

	// JPQL UPDATE

	@PutMapping("/update/{address}/{name}")
	@ApiOperation(value = "Update Student Deatails by Name", response = Student.class)
	public String updatead(@PathVariable String address, @PathVariable String name) {
		service.addressUpdate(address, name);
		return "Update Success";
	}

	// JPQL DELETE
	@DeleteMapping("/delete/{name}")
	@ApiOperation(value = "Delete Student Deatails by Name", response = Student.class)
	public String deleteStudentDetails(@PathVariable String name) {
		service.deleteStudent(name);
		return "Deleted Student with name " + name;
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Student Deatails by Id", response = Student.class)
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "Deleted Student with id " + id;
	}
}
