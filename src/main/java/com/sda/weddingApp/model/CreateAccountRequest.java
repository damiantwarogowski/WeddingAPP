package com.sda.weddingApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {
    private String username;
    private String password;
    private String confirmPassword;

    private String firstName;
    private String lastName;

    private String email;
}
