package com.chndnsharma.project.hospitalManagement.repository;

import com.chndnsharma.project.hospitalManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
