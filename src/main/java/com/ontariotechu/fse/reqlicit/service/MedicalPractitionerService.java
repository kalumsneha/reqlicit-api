package com.ontariotechu.fse.reqlicit.service;

import com.ontariotechu.fse.reqlicit.model.MedicalPractitioner;

import java.util.List;
import java.util.Optional;

public interface MedicalPractitionerService {
    MedicalPractitioner createMedicalPractitioner(MedicalPractitioner medicalPractitioner);

    List<MedicalPractitioner> getMedicalPractitioners();

    Optional<MedicalPractitioner> getMedicalPractitionerById(Integer id);

    MedicalPractitioner updateMedicalPractitioner(Integer id, MedicalPractitioner medicalPractitioner);
}
