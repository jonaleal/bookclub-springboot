package com.udea.bookclub.repositories;

import com.udea.bookclub.models.UserBookClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserBookClubRepository extends JpaRepository<UserBookClub, Long> {
}
