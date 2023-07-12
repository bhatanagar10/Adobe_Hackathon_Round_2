package com.adobe.round2.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/*
 *  This is an entity class, which has necessary validations added
 *  lombok is used to reduce the boilerplate code for getter setter constructors and overriding toString() method
 */
@Data
public class Details {

    @NotNull(message = "Template ID must be provided")
    @NotBlank(message = "Template ID cannot be blank")
    public String template_id;

    @NotNull(message = "Personal Information must be provided")
    @Valid
    public PersonalInfo personal_information;

    @NotNull(message = "Job title must be provided")
    @NotBlank(message = "Job title must not be blank")
    public String job_title;

    @NotNull(message = "career objectives must be provided")
    public String career_objective;

    @NotNull(message = "skills must be provided" )
    public List<String> skills;

    @Valid
    @NotNull(message = "Education info must be provided")
    public List<EducationInfo> education;

    @Valid
    @NotNull(message = "Experience must be provided")
    public List<ExperienceInfo> experience;

    @Valid
    @NotNull(message =  "Achievements must be provided")
    public List<AchievementInfo> achievements;


    public String list_of_skills;



}
