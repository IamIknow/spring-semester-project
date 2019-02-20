package ru.omgups.courseproject.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("No user found with id: " + id);
    }
}
