package com.depromeet.health.payload;

import com.depromeet.health.model.enums.Answer;
import com.depromeet.health.model.enums.GenderType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {
    @JsonProperty("email")
    String email;

    @JsonProperty("name")
    String name;

    @JsonProperty("profile")
    String profile;

    @JsonProperty("uid")
    String uid;

    @JsonProperty("survey")
    List<Survey> survey;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Survey> getSurvey() {
        return survey;
    }

    public void setSurvey(List<Survey> survey) {
        this.survey = survey;
    }

    public GenderType getGenderInSurveys() {
        Optional<Survey> optionalSurvey = this.survey.stream().filter(c -> c.getTitle().equals("gender")).findFirst();
        Survey survey = optionalSurvey.orElseThrow(IllegalArgumentException::new);

        if(survey.getTitle().equals(Answer.a.getAnswer())) {
            return GenderType.MAN;
        }else if(survey.getTitle().equals(Answer.b.getAnswer())) {
            return GenderType.WOMAN;
        }

        return GenderType.NONE;
    }

    public int getYearsOfExerciseInSurveys() {
        Optional<Survey> optionalSurvey = this.survey.stream().filter(c -> c.getTitle().equals("yearsOfExercise")).findFirst();
        Survey survey = optionalSurvey.orElseThrow(IllegalArgumentException::new);

        if(survey.getTitle().equals(Answer.a.getAnswer())){
            return 1;
        }else if(survey.getTitle().equals(Answer.b.getAnswer())){
            return 3;
        }else if(survey.getTitle().equals(Answer.c.getAnswer())){
            return 5;
        }else if(survey.getTitle().equals(Answer.d.getAnswer())){
            return 7;
        }
        return -1;
    }
}
