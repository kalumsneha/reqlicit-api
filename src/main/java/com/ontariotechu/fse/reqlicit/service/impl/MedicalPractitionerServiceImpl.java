package com.ontariotechu.fse.reqlicit.service.impl;

import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.MedicalPractitioner;
import com.ontariotechu.fse.reqlicit.model.Patient;
import com.ontariotechu.fse.reqlicit.model.User;
import com.ontariotechu.fse.reqlicit.repository.MedicalPractitionerRepository;
import com.ontariotechu.fse.reqlicit.service.MedicalPractitionerService;
import com.ontariotechu.fse.reqlicit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicalPractitionerServiceImpl implements MedicalPractitionerService {

    @Autowired
    private MedicalPractitionerRepository medicalPractitionerRepository;

    @Autowired
    private UserService userService;

    @Override
    public MedicalPractitioner createMedicalPractitioner(MedicalPractitioner medicalPractitioner) {
        //validate userid of medical practitioner
        User user = this.userService.getUserById(medicalPractitioner.getUserId()).orElseThrow(() -> new NotFoundException("Could not find user by the provided id"));
        //medicalPractitioner.setUser(user);
        return this.medicalPractitionerRepository.save(medicalPractitioner);
    }

    @Override
    public List<MedicalPractitioner> getMedicalPractitioners() {
        return this.medicalPractitionerRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    @Override
    public Optional<MedicalPractitioner> getMedicalPractitionerById(Integer id) {
        return this.medicalPractitionerRepository.findById(id);
    }

    @Override
    public MedicalPractitioner updateMedicalPractitioner(Integer id, MedicalPractitioner medicalPractitioner) {
        MedicalPractitioner medicalPractitionerUpdate = this.medicalPractitionerRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not find medical practitioner by the provided id"));
        medicalPractitionerUpdate.setName(medicalPractitioner.getName());
        medicalPractitionerUpdate.setSpecialty(medicalPractitioner.getSpecialty());
        User user = this.userService.getUserById(medicalPractitioner.getUserId()).orElseThrow(() -> new NotFoundException("Could not find user by the provided id"));
        medicalPractitionerUpdate.setUserId(medicalPractitioner.getUserId());
        //medicalPractitionerUpdate.setUser(user);
        return this.medicalPractitionerRepository.save(medicalPractitionerUpdate);
    }
}
