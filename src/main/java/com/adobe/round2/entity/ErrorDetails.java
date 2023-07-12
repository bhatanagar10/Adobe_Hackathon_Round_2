package com.adobe.round2.entity;

import lombok.Data;

import java.time.LocalDateTime;

/*
 * This is an entity class for exception response
 */
@Data
public class ErrorDetails {
    private String message;

    public ErrorDetails( String message) {
        this.message = message;
    }
}
