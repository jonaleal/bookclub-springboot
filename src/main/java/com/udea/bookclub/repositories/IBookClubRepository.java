package com.udea.bookclub.repositories;

import com.udea.bookclub.models.BookClub;
import com.udea.bookclub.models.User;
import com.udea.bookclub.models.UserBookClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookClubRepository extends JpaRepository<BookClub, Long> {
}
