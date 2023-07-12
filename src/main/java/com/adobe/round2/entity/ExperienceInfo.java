package com.adobe.round2.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/*
 *  This is an entity class, which has necessary validations added
 *  lombok is used to reduce the boilerplate code for getter setter constructors and overriding toString() method
 */
@Data
public class ExperienceInfo {
    @NotNull(message = "company name must be provided")
    public String company_name;
    @NotNull(message = "passing year must be provided")
    public String passing_year;
    @NotNull(message = "responsibilities must be provided")
    public String responsibilities;
}
