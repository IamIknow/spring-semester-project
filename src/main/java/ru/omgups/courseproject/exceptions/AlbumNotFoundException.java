package ru.omgups.courseproject.exceptions;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(Long id) {
        super("No album found with id:" + id);
    }
}
