package com.example.batchupdate.examplebatchupdate.controller;

import com.example.batchupdate.examplebatchupdate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/withBatchUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void withBatchUpdate() {
        userService.batchUpdateTest();
    }

    @GetMapping("/withUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void withoutBatchUpdate() {
        userService.multipleUpdateTest();
    }
}
