package com.ontariotechu.fse.reqlicit.service.impl;

import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.MedicalPractitioner;
import com.ontariotechu.fse.reqlicit.model.Patient;
import com.ontariotechu.fse.reqlicit.model.User;
import com.ontariotechu.fse.reqlicit.repository.PatientRepository;
import com.ontariotechu.fse.reqlicit.service.PatientService;
import com.ontariotechu.fse.reqlicit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserService userService;

    @Override
    public Patient createPatient(Patient patient) {
        //validate userid of patient
        User user = this.userService.getUserById(patient.getUserId()).orElseThrow(() -> new NotFoundException("Could not find user by the provided id"));
        //patient.setUser(user);
        return this.patientRepository.save(patient);
    }

    @Override
    public List<Patient> getPatients() {
        return this.patientRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    @Override
    public Optional<Patient> getPatientById(Integer id) {
        return this.patientRepository.findById(id);
    }

    @Override
    public Patient updatePatient(Integer id, Patient patient) {
        Patient patientToUpdate = this.patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not find patient by the provided id"));
        patientToUpdate.setName(patient.getName());
        patientToUpdate.setAge(patient.getAge());
        User user = this.userService.getUserById(patient.getUserId()).orElseThrow(() -> new NotFoundException("Could not find user by the provided id"));
        patientToUpdate.setUserId(patient.getUserId());
        //patientToUpdate.setUser(user);
        return this.patientRepository.save(patientToUpdate);
    }
}
