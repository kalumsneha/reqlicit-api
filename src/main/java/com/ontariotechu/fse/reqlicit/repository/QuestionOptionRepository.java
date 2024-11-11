package com.ontariotechu.fse.reqlicit.repository;

import com.ontariotechu.fse.reqlicit.model.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {
    List<QuestionOption> findByQuestionId(Integer questionId);

    void deleteAllByQuestionId(Integer id);
}
