package com.management.blogwebsite.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {

    //Entity wird zum Speichern und Übergeben von Daten aus der Datenbank genutzt
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String url;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    private String shortDescription;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;


    //post ist der owner davon. Das müssen wir hibernate mitteilen
    // Wenn wir den Post löschen, dann soll es auch die Kommentare löschen, daher REMOVE
    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();


}
