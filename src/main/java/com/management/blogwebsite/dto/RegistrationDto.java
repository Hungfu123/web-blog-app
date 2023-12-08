package com.management.blogwebsite.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Bitte Vorname eingeben!")
    private String firstName;
    @NotEmpty(message = "Bitte Nachname eingeben!")
    private String lastName;
    @NotEmpty(message = "Bitte eine E-Mail Adresse eingeben!")
    @Email(message = "Bitte eine gültige E-mail Adresse eingeben!")
    private String email;
    @NotEmpty(message = "Bitte Passwort eingeben!")
    private String password;

}
