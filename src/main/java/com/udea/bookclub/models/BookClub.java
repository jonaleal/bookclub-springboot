package com.udea.bookclub.models;

import com.udea.bookclub.utils.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "book_clubs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class BookClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "tags", columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> tags;

    @Column(name = "meet_link", length = 255)
    private String meetLink;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}

