package com.ontariotechu.fse.reqlicit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.MedicalPractitioner;
import com.ontariotechu.fse.reqlicit.service.MedicalPractitionerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/medical-practitioner")
@Slf4j
public class MedicalPractitionerController {

    @Autowired
    private MedicalPractitionerService medicalPractitionerService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public MedicalPractitioner createMedicalPractitioner(@RequestBody MedicalPractitioner medicalPractitioner) throws JsonProcessingException {
        log.info("creating medical practitioner. Medical Practitioner: {}", new ObjectMapper().writeValueAsString(medicalPractitioner));
        return this.medicalPractitionerService.createMedicalPractitioner(medicalPractitioner);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<MedicalPractitioner> getMedicalPractitioners() throws JsonProcessingException {
        log.info("fetching medical practitioners..");
        return this.medicalPractitionerService.getMedicalPractitioners();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Optional<MedicalPractitioner> getMedicalPractitionerById(@PathVariable Integer id) throws JsonProcessingException {
        log.info("fetching medical practitioner by id. id: {}", id);
        return Optional.ofNullable(this.medicalPractitionerService.getMedicalPractitionerById(id).orElseThrow(() -> new NotFoundException("Could not find medical practitioner by the provided id")));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public MedicalPractitioner updateMedicalPractitioner(@PathVariable Integer id, @RequestBody MedicalPractitioner medicalPractitioner) throws JsonProcessingException {
        log.info("updating medical practitioner. id: {}, Medical Practitioner: {}", id, new ObjectMapper().writeValueAsString(medicalPractitioner));
        return this.medicalPractitionerService.updateMedicalPractitioner(id, medicalPractitioner);
    }

}
