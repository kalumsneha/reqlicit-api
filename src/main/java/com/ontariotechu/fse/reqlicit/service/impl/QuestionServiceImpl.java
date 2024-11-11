package com.ontariotechu.fse.reqlicit.service.impl;

import com.ontariotechu.fse.reqlicit.exception.type.BadRequestException;
import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.MedicalPractitioner;
import com.ontariotechu.fse.reqlicit.model.Question;
import com.ontariotechu.fse.reqlicit.model.QuestionOption;
import com.ontariotechu.fse.reqlicit.repository.QuestionOptionRepository;
import com.ontariotechu.fse.reqlicit.repository.QuestionRepository;
import com.ontariotechu.fse.reqlicit.service.MedicalPractitionerService;
import com.ontariotechu.fse.reqlicit.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MedicalPractitionerService medicalPractitionerService;

    @Autowired
    private QuestionOptionRepository questionOptionRepository;

    @Transactional
    @Override
    public Question createQuestion(Question question) {
        //validate medical practitioner
        MedicalPractitioner medicalPractitioner = this.medicalPractitionerService.getMedicalPractitionerById(question.getMedicalPractitionerId()).orElseThrow(() -> new NotFoundException("Could not find medical practitioner by the provided practitioner id"));
        //question.setMedicalPractitioner(medicalPractitioner);

        //validate question type and options
        if(question.getQuestionType().isMultichoice()){
            if(ObjectUtils.isEmpty(question.getQuestionOptions())){
                throw new BadRequestException("Question Options are required for the selected Question Type");
            }
            Question savedQuestion = this.questionRepository.save(question);;
            for(QuestionOption questionOption : question.getQuestionOptions()){
                questionOption.setQuestion(savedQuestion);
            }
            this.questionOptionRepository.saveAll(question.getQuestionOptions());
            return savedQuestion;
        }

        return this.questionRepository.save(question);
    }

    //TODO
    //restrict view of questions to the patients, if they are hidden.
    @Override
    public List<Question> getQuestions(Integer medicalPractitionerId) {
        List<Question> questions = this.questionRepository.findAllByMedicalPractitionerId(medicalPractitionerId, Sort.by(Sort.Direction.DESC, "medicalPractitionerId"));

        for(Question question : questions){
            if(question.getQuestionType().isMultichoice())
                question.setQuestionOptions(this.questionOptionRepository.findByQuestionId(question.getId()));
        }

        return questions;
    }

    //TODO
    //restrict view of questions to the patients, if they are hidden.
    @Override
    public Optional<Question> getQuestionById(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent() && question.get().getQuestionType().isMultichoice()){
            question.get().setQuestionOptions(this.questionOptionRepository.findByQuestionId(question.get().getId()));
        }
        return question;
    }

    @Transactional
    @Override
    public Question updateQuestion(Integer id, Question question) {
        Question questionToUpdate = this.questionRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not find question by the provided id"));
        questionToUpdate.setHideQuestion(question.isHideQuestion());
        questionToUpdate.setQuestionText(question.getQuestionText());
        questionToUpdate.setQuestionType(question.getQuestionType());

        //validate question type and options
        this.questionOptionRepository.deleteAllByQuestionId(questionToUpdate.getId());
        if(question.getQuestionType().isMultichoice()){
            if(ObjectUtils.isEmpty(question.getQuestionOptions())){
                throw new BadRequestException("Question Options are required for the selected Question Type");
            }
            Question savedQuestion = this.questionRepository.save(questionToUpdate);

            for(QuestionOption questionOption : question.getQuestionOptions()){
                questionOption.setQuestion(savedQuestion);
            }
            this.questionOptionRepository.saveAll(question.getQuestionOptions());
            return savedQuestion;
        }

        return this.questionRepository.save(questionToUpdate);
    }
}
