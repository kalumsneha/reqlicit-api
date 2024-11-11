package com.ontariotechu.fse.reqlicit.repository;

import com.ontariotechu.fse.reqlicit.model.MedicalPractitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalPractitionerRepository extends JpaRepository<MedicalPractitioner, Integer> {
}
