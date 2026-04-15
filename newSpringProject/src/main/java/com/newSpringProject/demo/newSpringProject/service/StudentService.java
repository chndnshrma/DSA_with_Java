package com.newSpringProject.demo.newSpringProject.service;

import com.newSpringProject.demo.newSpringProject.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentsById(Long id);
}
