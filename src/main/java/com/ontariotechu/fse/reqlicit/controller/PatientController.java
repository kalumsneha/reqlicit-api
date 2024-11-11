package com.ontariotechu.fse.reqlicit.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.Patient;
import com.ontariotechu.fse.reqlicit.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patient")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) throws JsonProcessingException {
        log.info("creating patient. Patient: {}", new ObjectMapper().writeValueAsString(patient));
        return this.patientService.createPatient(patient);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Patient> getPatients() throws JsonProcessingException {
        log.info("fetching patients..");
        return this.patientService.getPatients();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Integer id) throws JsonProcessingException {
        log.info("fetching patient by id. id: {}", id);
        return Optional.ofNullable(this.patientService.getPatientById(id).orElseThrow(() -> new NotFoundException("Could not find patient by the provided id")));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Integer id, @RequestBody Patient patient) throws JsonProcessingException {
        log.info("updating patient. id: {}, patient: {}", id, new ObjectMapper().writeValueAsString(patient));
        return this.patientService.updatePatient(id, patient);
    }

}
