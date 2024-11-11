package com.ontariotechu.fse.reqlicit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ontariotechu.fse.reqlicit.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Base64;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medical_practitioners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalPractitioner extends BaseEntity {

    @NotEmpty
    private String name;

    @NotEmpty
    private String specialty;

    @Column(unique=true)
    @NotNull
    private Integer userId;

    //private User user;
}
