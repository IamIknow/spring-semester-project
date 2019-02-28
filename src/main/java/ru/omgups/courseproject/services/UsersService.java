package ru.omgups.courseproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omgups.courseproject.dtos.UserDto;
import ru.omgups.courseproject.entities.User;
import ru.omgups.courseproject.exceptions.EmailExistsException;
import ru.omgups.courseproject.repositories.UsersRepository;

@Service
public class UsersService {
    private UsersRepository repository;

    @Autowired
    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public User registerNewUserAccount(UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            +  accountDto.getEmail());
        }

        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
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
}
