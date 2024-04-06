package com.udea.bookclub.repositories;

import com.udea.bookclub.models.BookClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookClubRepository extends JpaRepository<BookClub, Long> {
}
