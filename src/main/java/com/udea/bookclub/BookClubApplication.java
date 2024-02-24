package com.udea.bookclub;

import com.udea.bookclub.dtos.BookClubDTO;
import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.models.BookClub;
import com.udea.bookclub.services.BookClubService;
import com.udea.bookclub.services.facade.IBookClubService;
import com.udea.bookclub.services.facade.IUserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class BookClubApplication implements ApplicationRunner {
    private final IUserService userService;
    private final IBookClubService bookClubService;

    public BookClubApplication(IUserService userService, IBookClubService bookClubService) {
        this.userService = userService;
        this.bookClubService = bookClubService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookClubApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        List<UserDTO> users = List.of(
                new UserDTO(null, "admin", null, null, "admin"),
                new UserDTO(null, "username1", "img1.com", "email1@mail.com", "password1"),
                new UserDTO(null, "username2", "img2.com", "email2@mail.com", "password2"),
                new UserDTO(null, "username3", "img3.com", "email3@mail.com", "password3"),
                new UserDTO(null, "username4", "img4.com", "email4@mail.com", "password4")
        );
        users.forEach(userService::save);

        List<BookClubDTO> bookClubs = new ArrayList<>();
        bookClubs.add(new BookClubDTO(null, "Club de lectura 1", "Descripción del club de lectura 1", List.of("Terror", "Suspenso"), "meetlink1", 1L));

        bookClubs.forEach(bookClubService::save);
    }

    @GetMapping("/hello")
    public String greetings(){
        return "Hello";
    }
}
