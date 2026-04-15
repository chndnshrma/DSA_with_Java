package com.newSpringProject.demo.newSpringProject.service.impl;

import com.newSpringProject.demo.newSpringProject.dto.StudentDto;
import com.newSpringProject.demo.newSpringProject.entity.Student;
import com.newSpringProject.demo.newSpringProject.repository.StudentRepository;
import com.newSpringProject.demo.newSpringProject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentsById(Long id) {
        Student student =  studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with ID: "+id));
        return modelMapper.map(student, StudentDto.class);
    }
}
