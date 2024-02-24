package com.udea.bookclub.controllers;

import com.udea.bookclub.dtos.BookClubDTO;
import com.udea.bookclub.services.BookClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book Club")
@RestController
@RequestMapping("api/v1/bookclub/")
public class BookClubController {

    private BookClubService bookClubService;

    public BookClubController(BookClubService bookClubService) {
        this.bookClubService = bookClubService;
    }

    @PostMapping("/")
    @Operation(summary = "Create a bookclub")
    @ApiResponse(responseCode = "201", description = "Bookclub successfully created")
    @ApiResponse(responseCode = "409", description = "Something went wrong")
    public ResponseEntity<BookClubDTO> create(@RequestBody BookClubDTO bookClubDTO) {
        BookClubDTO savedBookClubD = bookClubService.save(bookClubDTO);
        if (savedBookClubD == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookClubD);
    }

    @GetMapping("/")
    @Operation(summary = "Get a page of bookclubs")
    @ApiResponse(responseCode = "200", description = "Bookclub successfully retrieved")
    @ApiResponse(responseCode = "204", description = "No bookclubs found")
    public ResponseEntity<List<BookClubDTO>> findAll(@ParameterObject Pageable pageable) {
        var bookClubs = bookClubService.findAll(pageable);
        if (bookClubs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookClubs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a bookclub by id")
    @ApiResponse(responseCode = "200", description = "Bookclub successfully retrieved")
    @ApiResponse(responseCode = "404", description = "Bookclub not found")
    public ResponseEntity<BookClubDTO> findById(@PathVariable Long id) {
        var bookClub = bookClubService.findById(id);
        if (bookClub == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookClub);
    }

    @PutMapping("/")
    @Operation(summary = "Update a bookclub")
    @ApiResponse(responseCode = "200", description = "Bookclub successfully updated")
    @ApiResponse(responseCode = "409", description = "Something went wrong")
    public ResponseEntity<BookClubDTO> update(@RequestBody BookClubDTO bookClubDTO) {
        BookClubDTO updatedBookClub = bookClubService.update(bookClubDTO);
        if (updatedBookClub == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedBookClub);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bookclub by id")
    @ApiResponse(responseCode = "200", description = "Bookclub successfully deleted")
    @ApiResponse(responseCode = "409", description = "Something went wrong")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookClubService.deleteById(id);
        if (bookClubService.findById(id) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
