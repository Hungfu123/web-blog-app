package com.management.blogwebsite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Bitte Name eingeben!")
    private String name;
    @NotEmpty(message = "Bitte Email eingeben!")
    @Email(message = "Bitte eine gültige Email eingeben!")
    private String email;
    @NotEmpty(message = "Bitte Inhalt eingeben!")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
