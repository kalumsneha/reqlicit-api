package com.ontariotechu.fse.reqlicit.service;

import com.ontariotechu.fse.reqlicit.model.Answer;

import java.util.List;

public interface AnswerService {
    Answer createAnswer(Answer answer);

    List<Answer> getAnswers(Integer patientId, Integer questionId);

    Answer updateAnswer(Integer id, Answer answer);
}
