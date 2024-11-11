package com.ontariotechu.fse.reqlicit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.Answer;
import com.ontariotechu.fse.reqlicit.service.AnswerService;
import com.ontariotechu.fse.reqlicit.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/answer")
@Slf4j
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) throws JsonProcessingException {
        log.info("creating answer. Answer: {}", new ObjectMapper().writeValueAsString(answer));
        return this.answerService.createAnswer(answer);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT') or hasRole('ROLE_MEDICAL_PRACTITIONER')")
    @GetMapping
    public List<Answer> getAnswers(
            @RequestParam(name = "patientId", required = false) Integer patientId,
            @RequestParam(name = "questionId", required = false) Integer questionId) throws JsonProcessingException {
        log.info("fetching answers to questions for a patient or particular question. patient id: {}, question id: {}", patientId, questionId);
        return this.answerService.getAnswers(patientId, questionId);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping("/{id}")
    public Answer updateAnswer(@PathVariable Integer id, @RequestBody Answer answer) throws JsonProcessingException {
        log.info("updating answer. answer: {}, answer: {}", id, new ObjectMapper().writeValueAsString(answer));
        return this.answerService.updateAnswer(id, answer);
    }
    
}
