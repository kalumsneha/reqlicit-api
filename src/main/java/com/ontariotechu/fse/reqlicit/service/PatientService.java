package com.ontariotechu.fse.reqlicit.service;

import com.ontariotechu.fse.reqlicit.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient createPatient(Patient patient);

    List<Patient> getPatients();

    Optional<Patient> getPatientById(Integer id);

    Patient updatePatient(Integer id, Patient patient);
}
