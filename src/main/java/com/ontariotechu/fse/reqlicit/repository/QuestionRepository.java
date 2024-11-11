package com.ontariotechu.fse.reqlicit.repository;

import com.ontariotechu.fse.reqlicit.model.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByMedicalPractitionerId(Integer medicalPractitionerId, Sort by);
}
