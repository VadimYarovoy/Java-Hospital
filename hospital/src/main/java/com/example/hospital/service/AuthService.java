package com.example.hospital.service;

import java.util.List;

public interface AuthService {
    void addUser(String username, String password);
    void deleteUser(String username);
    List<String> listUsers();
}
