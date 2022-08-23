package com.example.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.hospital.entity.User;
import com.example.hospital.exception.UserAlreadyExistsException;
import com.example.hospital.exception.UserNotFoundException;
import com.example.hospital.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void addUser(String username, String password) {
        Optional<User> user =  userRepository.findUserByName(username);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        userRepository.save(
                new User(username, encoder.encode(password), Collections.singletonList("ROLE_USER"))
        );
    }

    @Override
    public void deleteUser(String username) {
        Optional<User> opt =  userRepository.findUserByName(username);
        if (!opt.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        User user = opt.get();
        if (user.getName().equals("admin")) {
            throw new IllegalArgumentException("Cannot delete admin");
        }

        userRepository.delete(user);
    }

    @Override
    public List<String> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> u.getName())
                .toList();
    }
}
