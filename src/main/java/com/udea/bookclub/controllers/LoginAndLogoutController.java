package com.udea.bookclub.controllers;

import com.udea.bookclub.dtos.LoginAndSignUpDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Login and Logout")
@RestController
@RequestMapping("api/v1/")
public class LoginAndLogoutController {

    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest httpServletRequest;
    private final SecurityContextLogoutHandler securityContextLogoutHandler;
    private final HttpServletResponse httpServletResponse;

    public LoginAndLogoutController(AuthenticationManager authenticationManager, HttpServletRequest httpServletRequest, SecurityContextLogoutHandler securityContextLogoutHandler, HttpServletResponse httpServletResponse) {
        this.authenticationManager = authenticationManager;
        this.httpServletRequest = httpServletRequest;
        this.securityContextLogoutHandler = securityContextLogoutHandler;
        this.httpServletResponse = httpServletResponse;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginAndSignUpDTO loginRequest) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        try {
            Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authenticationResponse);
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
            return ResponseEntity.ok("Successful authentication");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed authentication");
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, authentication);
            return ResponseEntity.ok("Closed session");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("There is not user logged");
        }
    }
}