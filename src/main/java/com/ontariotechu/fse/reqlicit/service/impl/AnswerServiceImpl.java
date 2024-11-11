package com.ontariotechu.fse.reqlicit.service.impl;

import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.Answer;
import com.ontariotechu.fse.reqlicit.model.Patient;
import com.ontariotechu.fse.reqlicit.model.Question;
import com.ontariotechu.fse.reqlicit.repository.AnswerRepository;
import com.ontariotechu.fse.reqlicit.service.AnswerService;
import com.ontariotechu.fse.reqlicit.service.PatientService;
import com.ontariotechu.fse.reqlicit.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private QuestionService questionService;

    @Override
    public Answer createAnswer(Answer answer) {
        //validate patient and question exists
        Question question = this.questionService.getQuestionById(answer.getQuestionId()).orElseThrow(() -> new NotFoundException("Could not find question by the provided question id"));
        Patient patient = this.patientService.getPatientById(answer.getPatientId()).orElseThrow(() -> new NotFoundException("Could not find patient by the provided patient id"));

        //answer.setQuestion(question);
        //answer.setPatient(patient);

        return this.answerRepository.save(answer);
    }

    @Override
    public List<Answer> getAnswers(Integer patientId, Integer questionId) {
        return this.answerRepository.findAllByPatientIdOrQuestionId(patientId, questionId);
    }

    @Override
    public Answer updateAnswer(Integer id, Answer answer) {
        Answer answerToUpdate = this.answerRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not find answer by the provided id"));
        answerToUpdate.setAnswerText(answer.getAnswerText());
        return this.answerRepository.save(answerToUpdate);
    }

}
