package com.adobe.round2.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/*
 *  This is an entity class, which has necessary validations added
 *  lombok is used to reduce the boilerplate code for getter setter constructors and overriding toString() method
 */
@Data
public class PersonalInfo {

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    public String name;

    @NotBlank(message = "last name must not be blank")
    @NotNull(message = "last name must not be null")
    public String last_name;

    @NotBlank(message = "email must not be blank")
    @NotNull(message = "email must be provided")
    public String email_address;

    @NotBlank(message = "phone number must not be blank")
    @NotNull(message = "phone number must be provided")
    public String phone_number;

    @NotBlank(message = "linkedin url must not be blank")
    @NotNull(message =  "linkedin url must be provided")
    public String linkedin_url;
}
