package com.newSpringProject.demo.newSpringProject.service.impl;

import com.newSpringProject.demo.newSpringProject.dto.AddStudentRequestDto;
import com.newSpringProject.demo.newSpringProject.dto.StudentDto;
import com.newSpringProject.demo.newSpringProject.entity.Student;
import com.newSpringProject.demo.newSpringProject.repository.StudentRepository;
import com.newSpringProject.demo.newSpringProject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public StudentDto createStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto,Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exist with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        // 1. Fetch the existing record
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student does not exist with ID: " + id));
        modelMapper.map(addStudentRequestDto, student);
        student.setId(id);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public @Nullable StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student does not exist with ID: " + id));

        updates.forEach((field, value) -> {
            switch (field) {
                case "name" : student.setName((String) value);
                break;
                case "email" : student.setEmail((String) value);
                break;
                default: throw new IllegalArgumentException("Field not supported!");
            }
        });

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
