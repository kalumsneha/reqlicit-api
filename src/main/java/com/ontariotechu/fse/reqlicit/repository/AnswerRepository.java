package com.ontariotechu.fse.reqlicit.repository;

import com.ontariotechu.fse.reqlicit.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByPatientIdOrQuestionId(Integer patientId, Integer questionId);
}
