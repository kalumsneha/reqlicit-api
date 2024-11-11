package com.ontariotechu.fse.reqlicit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.Question;
import com.ontariotechu.fse.reqlicit.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/question")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PreAuthorize("hasRole('ROLE_MEDICAL_PRACTITIONER')")
    @PostMapping
    public Question createQuestion(@RequestBody Question question) throws JsonProcessingException {
        log.info("creating question. Question: {}", new ObjectMapper().writeValueAsString(question));
        return this.questionService.createQuestion(question);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT') or hasRole('ROLE_MEDICAL_PRACTITIONER')")
    @GetMapping("/{medicalPractitionerId}")
    public List<Question> getQuestions(@PathVariable Integer medicalPractitionerId) throws JsonProcessingException {
        log.info("fetching questions by medical practitioner id. id: {}", medicalPractitionerId);
        return this.questionService.getQuestions(medicalPractitionerId);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT') or hasRole('ROLE_MEDICAL_PRACTITIONER')")
    @GetMapping
    public Optional<Question> getQuestionInId(@RequestParam Integer id) throws JsonProcessingException {
        log.info("fetching question by id. id: {}", id);
        return Optional.ofNullable(this.questionService.getQuestionById(id).orElseThrow(() -> new NotFoundException("Could not find question by the provided id")));
    }

    @PreAuthorize("hasRole('ROLE_MEDICAL_PRACTITIONER')")
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Integer id, @RequestBody Question question) throws JsonProcessingException {
        log.info("updating question. question: {}, question: {}", id, new ObjectMapper().writeValueAsString(question));
        return this.questionService.updateQuestion(id, question);
    }

}
