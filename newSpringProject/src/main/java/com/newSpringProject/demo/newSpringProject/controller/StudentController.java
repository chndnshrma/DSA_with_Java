package com.newSpringProject.demo.newSpringProject.controller;

import com.newSpringProject.demo.newSpringProject.dto.StudentDto;
import com.newSpringProject.demo.newSpringProject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<StudentDto> getStudents() {
        //return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.OK(studentService.getAllStudents());
    }
    @GetMapping("/students/{id}")
    public  ResponseEntity<StudentDto> getStudentsById(@PathVariable Long id) {
        return ResponseEntity.OK(studentService.getStudentsById(id));
    }
    @PostMapping
    public ResponseEntity<StudentDto>() {

    }

}
