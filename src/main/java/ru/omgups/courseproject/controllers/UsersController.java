package ru.omgups.courseproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.omgups.courseproject.dtos.UserDto;
import ru.omgups.courseproject.entities.User;
import ru.omgups.courseproject.exceptions.EmailExistsException;
import ru.omgups.courseproject.exceptions.UserNotFoundException;
import ru.omgups.courseproject.repositories.UsersRepository;
import ru.omgups.courseproject.services.UsersService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsersController {

    private final UsersRepository repository;
    private final UsersService service;

    @Autowired
    UsersController(UsersRepository repository, UsersService service) {
        this.repository = repository;
        this.service = service;
    }

    @RequestMapping("/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
        return repository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    return repository.save(newUser);
                }).orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/users/registration")
    public User registerUser(@Valid UserDto accountDto, BindingResult result) {
        User registered = null;
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto);
        }
        return registered;
    }

    private User createUserAccount(UserDto accountDto) {
        User registered;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}
