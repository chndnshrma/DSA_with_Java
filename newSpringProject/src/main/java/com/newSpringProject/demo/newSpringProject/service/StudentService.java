package com.newSpringProject.demo.newSpringProject.service;

import com.newSpringProject.demo.newSpringProject.dto.AddStudentRequestDto;
import com.newSpringProject.demo.newSpringProject.dto.StudentDto;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentsById(Long id);

    @Nullable StudentDto createStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    @Nullable StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    @Nullable StudentDto updatePartialStudent(Long id, Map<String,Object> updates);
}
