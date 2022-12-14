package com.example.hospital.web;

import com.example.hospital.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.hospital.dto.AuthRequest;
import com.example.hospital.entity.User;
import com.example.hospital.exception.UserAlreadyExistsException;
import com.example.hospital.exception.UserNotFoundException;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.security.jwt.JwtTokenProvider;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping(
            value = "/signin",
            consumes = "application/json"
    )
    public ResponseEntity<String> signIn(@RequestBody AuthRequest request) {
            String username = request.getUsername();
            String password = request.getPassword();
            Optional<User> user = userRepository.findUserByName(username);
            boolean passwordMatch = false;
            if (user.isPresent()) {
                User us = user.get();
                passwordMatch = encoder.matches(password, us.getPassword());
            }

            if (!passwordMatch)
                throw new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Invalid username or password"
                );

            String token = jwtTokenProvider.createToken(username, user.get().getRoles());
            return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping(
            value = "/add-user",
            consumes = "application/json"
    )
    public ResponseEntity addUser(@RequestBody AuthRequest request) {
        try {
            authService.addUser(
                    request.getUsername(),
                    request.getPassword()
            );
            return new ResponseEntity(HttpStatus.OK);
        } catch(UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping(
            value = "/delete-user",
            consumes = "application/json"
    )
    public ResponseEntity deleteUser(@RequestBody AuthRequest request) {
        try {
            authService.deleteUser(request.getUsername());
            return new ResponseEntity(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @GetMapping("/list-users")
    public ResponseEntity<List<String>> listUsers() {
        List<String> usernames = authService.listUsers();
        return new ResponseEntity<>(usernames, HttpStatus.OK);
    }
}
