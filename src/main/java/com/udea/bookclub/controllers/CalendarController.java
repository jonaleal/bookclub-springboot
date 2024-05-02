package com.udea.bookclub.controllers;

import com.udea.bookclub.dtos.EventRequest;
import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.services.facade.IBookClubService;
import com.udea.bookclub.services.facade.ICalendarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CalendarController {

    private final ICalendarService calendarService;
    private final IBookClubService bookClubService;

    public CalendarController(ICalendarService calendarService, IBookClubService bookClubService) {
        this.calendarService = calendarService;
        this.bookClubService = bookClubService;
    }

//    @PostMapping("/book-club/{bookClubId}/event")
//    public Void createEvent(@PathVariable Long bookClubId) {
//        List<UserDTO> users = bookClubService.findUsersByBookClubId(bookClubId);
//
//        List<String> emails = users.stream().map(UserDTO::email).toList();
//        System.out.println(emails);
//
//        return null;
//    }

    @GetMapping("/calendar/create-event")
    public String createEvent() {
        EventRequest eventRequest = new EventRequest(
                "Test event",
                "This is a test event",
                "2024-04-30T09:00:00-05:00",
                "2024-04-30T12:00:00-05:00",
                List.of("joleal@live.com", "carlos.sanchez7@udea.edu.co")
        );
        try {
            String resultEvent = calendarService.createEvent(eventRequest);
            return "Event created successfully: " + resultEvent;
        } catch (GeneralSecurityException | IOException e) {
            return "Error creating event";
        }
    }
}
