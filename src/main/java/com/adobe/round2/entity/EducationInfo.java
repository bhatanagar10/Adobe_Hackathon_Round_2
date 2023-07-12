package com.adobe.round2.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/*
 *  This is an entity class, which has necessary validations added
 *  lombok is used to reduce the boilerplate code for getter setter constructors and overriding toString() method
 */
@Data
public class EducationInfo {
    @NotNull(message = "school name must be provided")
    public String school_name;
    @NotNull(message = "passing year must be provided")
    public String passing_year;
    @NotNull(message =  "education description must be provided")
    public String description;

}
