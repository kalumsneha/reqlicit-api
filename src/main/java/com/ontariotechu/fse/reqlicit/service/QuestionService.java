package com.ontariotechu.fse.reqlicit.service;

import com.ontariotechu.fse.reqlicit.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question createQuestion(Question question);

    List<Question> getQuestions(Integer medicalPractitionerId);

    Optional<Question> getQuestionById(Integer id);

    Question updateQuestion(Integer id, Question question);
}
