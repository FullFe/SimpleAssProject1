package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.userRequestsNResponses.UserRequest;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/managment")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request){
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Пользователь создан");
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@Valid @RequestBody UserRequest request){
        userService.deleteUser(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Пользователь удален" + request.getLogin());
    }

    @Transactional
    @PatchMapping
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserRequest request){
        userService.updateUser(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Пользователь обновлен" + request.getLogin());
    }

}
