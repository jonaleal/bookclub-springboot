package com.udea.bookclub.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "users_book_clubs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class UserBookClub {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private BookClub bookClub;

}
