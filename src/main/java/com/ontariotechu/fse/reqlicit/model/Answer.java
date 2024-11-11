package com.ontariotechu.fse.reqlicit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ontariotechu.fse.reqlicit.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "answers", uniqueConstraints = { @UniqueConstraint(name = "UniqueQuestionIdAndPatientId", columnNames = { "questionId", "patientId" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer extends BaseEntity {

    @NotEmpty
    private String answerText;

    @NotNull
    private Integer questionId;

    @NotNull
    private Integer patientId;

    //private Question question;
    //private Patient patient;
}
