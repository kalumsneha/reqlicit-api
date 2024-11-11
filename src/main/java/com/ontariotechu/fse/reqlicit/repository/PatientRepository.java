package com.ontariotechu.fse.reqlicit.repository;

import com.ontariotechu.fse.reqlicit.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
