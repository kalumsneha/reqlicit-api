package com.ontariotechu.fse.reqlicit.model.enums;

public enum QuestionType {
    MULTICHOICE, OPEN;

    public boolean isMultichoice(){
        return this.name().equals(QuestionType.MULTICHOICE.name());
    }

    public boolean isOpenEnded(){
        return this.name().equals(QuestionType.OPEN.name());
    }

}
