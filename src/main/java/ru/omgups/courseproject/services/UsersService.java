package ru.omgups.courseproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.omgups.courseproject.dtos.UserDto;
import ru.omgups.courseproject.entities.User;
import ru.omgups.courseproject.exceptions.EmailExistsException;
import ru.omgups.courseproject.exceptions.UserNotFoundException;
import ru.omgups.courseproject.repositories.UsersRepository;

@Service
public class UsersService {
    private UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUserAccount(UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with this email address: "
                            +  accountDto.getEmail());
        }

        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRole("USER");
        return repository.save(user);
    }

    private boolean emailExists(String email) {
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getCurrentUser() {
        return (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
