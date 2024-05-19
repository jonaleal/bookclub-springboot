package com.udea.bookclub.controllers;

import com.udea.bookclub.dtos.EventRequest;
import com.udea.bookclub.dtos.ResponseDTO;
import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.services.facade.IBookClubService;
import com.udea.bookclub.services.facade.ICalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Tag(name = "Google Calendar")
@RestController
@RequestMapping("api/v1/book-club/")
public class CalendarController {

    private final ICalendarService calendarService;
    private final IBookClubService bookClubService;

    public CalendarController(ICalendarService calendarService, IBookClubService bookClubService) {
        this.calendarService = calendarService;
        this.bookClubService = bookClubService;
    }

    @PostMapping("/{bookClubId}/event")
    @Operation(summary = "Create an event in a bookclub")
    @ApiResponse(responseCode = "201", description = "Event successfully created")
    @ApiResponse(responseCode = "409", description = "Something went wrong")
    public ResponseEntity<ResponseDTO<String>> createEvent(@PathVariable Long bookClubId, @RequestBody EventData eventData) {
        List<UserDTO> users = bookClubService.findUsersByBookClubId(bookClubId);
        List<String> emails = users.stream().map(UserDTO::email).toList();

        EventRequest eventRequest = new EventRequest(
                eventData.summary(),
                eventData.description(),
                eventData.startDateTime(),
                eventData.endDateTime(),
                emails
        );

        try {
            String resultEvent = calendarService.createEvent(eventRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>("Event successfully created", resultEvent));
        } catch (GeneralSecurityException | IOException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO<>("Error creating event", null));
        }
    }

    //    @PostMapping("/event")
//    public String createEvent(@RequestBody EventRequest eventRequest) {
//        EventRequest eventRequest = new EventRequest(
//                "Test event",
//                "This is a test event",
//                "2024-04-30T09:00:00-05:00",
//                "2024-04-30T12:00:00-05:00",
//                List.of("joleal@live.com", "carlos.sanchez7@udea.edu.co")
//        );
//        try {
//            String resultEvent = calendarService.createEvent(eventRequest);
//            return "Event created successfully";
//        } catch (GeneralSecurityException | IOException e) {
//            return "Error creating event";
//        }
//    }
    public record EventData(
            String summary,
            String description,
            String startDateTime,
            String endDateTime) {
    }
}