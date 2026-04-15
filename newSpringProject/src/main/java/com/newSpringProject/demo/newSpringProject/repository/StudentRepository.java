package com.newSpringProject.demo.newSpringProject.repository;

import com.newSpringProject.demo.newSpringProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// 1. Must be an interface
// 2. Use 'Long' instead of 'long'
public interface StudentRepository extends JpaRepository<Student, Long> {

}