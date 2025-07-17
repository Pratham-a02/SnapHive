package com.example.SnapHive.SnapHive.controller;

import com.example.SnapHive.SnapHive.dto.LoginDto;
import com.example.SnapHive.SnapHive.dto.SignupDto;
import com.example.SnapHive.SnapHive.entities.User;
import com.example.SnapHive.SnapHive.exceptions.UserException;
import com.example.SnapHive.SnapHive.services.AuthService;
import com.example.SnapHive.SnapHive.services.UserService;
import com.example.SnapHive.SnapHive.services.UserUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserUserDetailsService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupDto signupDto) throws UserException {
        User user = userService.registerUser(signupDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }
}