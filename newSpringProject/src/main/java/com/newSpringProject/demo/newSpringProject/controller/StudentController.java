package com.newSpringProject.demo.newSpringProject.controller;

import com.newSpringProject.demo.newSpringProject.dto.AddStudentRequestDto;
import com.newSpringProject.demo.newSpringProject.dto.StudentDto;
import com.newSpringProject.demo.newSpringProject.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<StudentDto> getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body((StudentDto) studentService.getAllStudents());

    }

    @GetMapping("/{id}")
    public  ResponseEntity<StudentDto> getStudentsById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentsById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAStudent (@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody AddStudentRequestDto addStudentRequestDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, addStudentRequestDto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
    }
}
