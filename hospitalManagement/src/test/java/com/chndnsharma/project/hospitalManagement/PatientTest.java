package com.chndnsharma.project.hospitalManagement;

import com.chndnsharma.project.hospitalManagement.entity.Patient;
import com.chndnsharma.project.hospitalManagement.repository.PatientRepository;
import com.chndnsharma.project.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository() {
        // Now patientRepository should be injected correctly
        List<Patient> patients = patientRepository.findAll();
        System.out.println(patients);
    }

    @Test
    public void testTransactionMethods() {
        Patient patient = patientService.getPatientById(1L);
        System.out.println(patient);
    }
}