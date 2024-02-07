package com.udea.bookclub;

import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.services.facade.IUserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;


@SpringBootApplication
@Component
public class BookClubApplication implements ApplicationRunner {
    private final IUserService userService;

    public BookClubApplication(IUserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookClubApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<UserDTO> users = List.of(
                new UserDTO(null, "username1", "img1.com", "email1@mail.com", "password1"),
                new UserDTO(null, "username2", "img2.com", "email2@mail.com", "password2"),
                new UserDTO(null, "username3", "img3.com", "email3@mail.com", "password3"),
                new UserDTO(null, "username4", "img4.com", "email4@mail.com", "password4")
        );
        users.forEach(userService::save);
    }
}
