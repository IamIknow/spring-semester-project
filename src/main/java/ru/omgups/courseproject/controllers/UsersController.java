package ru.omgups.courseproject.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omgups.courseproject.entities.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    private List<User> users = new ArrayList<>();

    @RequestMapping("/users")
    public List<User> getUsers() {
        return users;
    }

}
