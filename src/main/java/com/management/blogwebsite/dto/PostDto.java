package com.management.blogwebsite.dto;

import com.management.blogwebsite.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    //Dto für Transfer zwischen dem Controller Layer und View Layer
    // Dient für das Form Binding
    //Repositories liefern Entities an den Service. Der Controller sollte aber mit der Art der Datenbeschaffung nichts zu tun haben und auch keine Entities kennen
    // Daher werden die vom Controller benötigten Daten am besten in ein Data Transfer Object (DTO) umgepackt.
    //DTOS enkoppeln die Schichten voneinander und sorgen für Wiederverwendbarkeit



    private Long id;
    @NotEmpty(message = "Bitte Titel eingeben!")
    private String title;
    private String url;
    @NotEmpty(message = "Inhalt sollte nicht leer sein!")
    private String content;
    @NotEmpty(message = "Kurzbeschreibung fehlt!")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Set<CommentDto> comments;
    private User createdBy;
}
