package ru.omgups.courseproject.controllers;

import org.springframework.web.bind.annotation.*;
import ru.omgups.courseproject.entities.Artist;
import ru.omgups.courseproject.exceptions.ArtistNotFoundException;
import ru.omgups.courseproject.repositories.ArtistsRepository;

import java.util.List;

@RestController
public class ArtistsController {

    private final ArtistsRepository repository;

    ArtistsController(ArtistsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("api/artists")
    public List<Artist> getArtists() {
        return repository.findAll();
    }

    @GetMapping("api/artists/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(id));
    }

    @PostMapping("api/artists")
    public Artist createArtist(@RequestBody Artist newArtist) {
        return repository.save(newArtist);
    }

    @PutMapping("api/artists/{id}")
    public Artist editArtist(@PathVariable Long id, @RequestBody Artist newArtist) {
        return repository.findById(id)
                .map(artist -> {
                    artist.setName(newArtist.getName());
                    return repository.save(artist);
                })
                .orElseGet(() -> {
                    newArtist.setId(id);
                    return repository.save(newArtist);
                });
    }

    @DeleteMapping("/artists/{id}")
    void deleteArtist(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
