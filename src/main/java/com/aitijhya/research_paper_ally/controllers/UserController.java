package com.aitijhya.research_paper_ally.controllers;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.*;

import com.aitijhya.research_paper_ally.User.User;
import com.aitijhya.research_paper_ally.requestDTO.UserDTO;
import com.aitijhya.research_paper_ally.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userDTO));
  }

  @GetMapping("/u/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable("email") @NotEmpty String email) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email));
  }

}
