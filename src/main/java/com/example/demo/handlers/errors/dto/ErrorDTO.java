package com.example.demo.handlers.errors.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements Serializable {

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String status;

}
