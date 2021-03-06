package ru.omgups.courseproject.exceptions;

public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(Long id) {
        super("Could not find artist with id: " + id);
    }
}
