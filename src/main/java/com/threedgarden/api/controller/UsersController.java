package com.threedgarden.api.controller;

import com.threedgarden.api.dto.AddressRequest;
import com.threedgarden.api.dto.LoginRequest;
import com.threedgarden.api.model.Users;
import com.threedgarden.api.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.addUser(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Users> updatedUser(@PathVariable("userId") Long id, @RequestBody Users updatedUser) {
        return ResponseEntity.ok(usersService.updatedUserById(id, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Users> deleteUser(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(usersService.deleteUserById(id));
    }

    @PostMapping("/{userId}/address")
    public ResponseEntity<Users> addUserAddress(@PathVariable("userId") Long id, @RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok(usersService.addUsersAddress(id, addressRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(usersService.loginUser(loginRequest.getEmail(),loginRequest.getPassword()));
    }
}
