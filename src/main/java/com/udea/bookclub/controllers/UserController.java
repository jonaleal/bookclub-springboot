package com.udea.bookclub.controllers;

import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.services.facade.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @Operation(summary = "Allows you to register an user")
    @ApiResponse(responseCode = "201", description = "User successfully created")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        UserDTO savedUser = userService.save(userDTO);
        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/")
    @Operation(summary = "Allows you to get a page of users")
    public ResponseEntity<List<UserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Allows you to get an user by id ")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/update")
    @Operation(summary = "Allows you to update an user")
    @ApiResponse(responseCode = "200", description = "User successfully updated")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.update(userDTO);
        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }
}
