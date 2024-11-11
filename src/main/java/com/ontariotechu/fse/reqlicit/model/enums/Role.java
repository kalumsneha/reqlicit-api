package com.ontariotechu.fse.reqlicit.model.enums;

public enum Role {
    ADMIN, MEDICAL_PRACTITIONER, PATIENT;

    public boolean isAdmin(){
        return this.name().equalsIgnoreCase(Role.ADMIN.name());
    }

    public boolean isMedicalPractitioner(){
        return this.name().equalsIgnoreCase(Role.MEDICAL_PRACTITIONER.name());
    }

    public boolean isPatient(){
        return this.name().equalsIgnoreCase(Role.PATIENT.name());
    }
}
