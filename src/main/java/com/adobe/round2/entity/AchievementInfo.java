package com.adobe.round2.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/*
 *  This is an entity class, which has necessary validations added
 *  lombok is used to reduce the boilerplate code for getter setter constructors and overriding toString() method
 */
@Data
public class AchievementInfo {
    @NotNull(message = "field must be provided")
    public String field;
    @NotNull(message = "awards must be provided")
    public String awards;
}
