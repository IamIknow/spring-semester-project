package ru.omgups.courseproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("api/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("api/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("api/users/{id}")
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

    @DeleteMapping("api/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping("api/users/registration")
    public User registerUser(@Valid @RequestBody UserDto accountDto, BindingResult result) {
        User registered = null;
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto);
            Authentication authentication = new UsernamePasswordAuthenticationToken(registered, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            registered.setPassword(null);   //TODO Proper password hiding
        }

        return registered;
    }

    @GetMapping("api/users/current")
    public User getCurrentUser() {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            return (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } else {
            return repository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        }
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
