package com.ontariotechu.fse.reqlicit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ontariotechu.fse.reqlicit.model.base.BaseEntity;
import com.ontariotechu.fse.reqlicit.model.enums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question extends BaseEntity {
    @NotEmpty
    private String questionText;
    @NotNull
    private QuestionType questionType;
    @NotNull
    private Integer medicalPractitionerId;

    private boolean hideQuestion;

    @Transient
    private List<QuestionOption> questionOptions;

    //private MedicalPractitioner medicalPractitioner;

}
