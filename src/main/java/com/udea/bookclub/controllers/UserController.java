package com.udea.bookclub.controllers;

import com.udea.bookclub.dtos.LoginAndSignUpDTO;
import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.services.facade.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User")
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @Operation(summary = "Register an user")
    @ApiResponse(responseCode = "201", description = "User successfully created")
    @ApiResponse(responseCode = "409", description = "Something went wrong")
    public ResponseEntity<UserDTO> create(@RequestBody LoginAndSignUpDTO signupRequest){
        UserDTO user = UserDTO.builder()
                .username(signupRequest.username())
                .password(signupRequest.password())
                .build();
        UserDTO savedUser = userService.save(user);
        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/")
    @Operation(summary = "Get a page of users")
    @ApiResponse(responseCode = "200", description = "Users successfully retrieved")
    @ApiResponse(responseCode = "204", description = "No users found")
    public ResponseEntity<List<UserDTO>> findAll(@ParameterObject Pageable pageable) {
        var users = userService.findAll(pageable);
        if (users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an user by id ")
    @ApiResponse(responseCode = "200", description = "User successfully retrieved")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/")
    @Operation(summary = "Update an user")
    @ApiResponse(responseCode = "200", description = "User successfully updated")
    @ApiResponse(responseCode = "409", description = "Something went wrong")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        UserDTO updateUser = userService.update(userDTO);
        if (updateUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }
}
