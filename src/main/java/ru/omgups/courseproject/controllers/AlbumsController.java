package ru.omgups.courseproject.controllers;

import org.springframework.web.bind.annotation.*;
import ru.omgups.courseproject.entities.Album;
import ru.omgups.courseproject.exceptions.AlbumNotFoundException;
import ru.omgups.courseproject.repositories.AlbumsRepository;
import ru.omgups.courseproject.services.AlbumsService;

import java.util.List;

@RestController
public class AlbumsController {
    private final AlbumsRepository repository;
    private final AlbumsService albumsService;

    AlbumsController(AlbumsRepository repository, AlbumsService albumsService) {
        this.repository = repository;
        this.albumsService = albumsService;
    }

    @GetMapping("api/artists/{id}/albums")
    public List<Album> getAlbums(@PathVariable Long id) {
        return this.repository.findAll();
    }

    @GetMapping("api/artists{artist-id}/albums/{id}")
    public Album getAlbumById(@PathVariable Long id, @PathVariable("artist-id") Long artistId) {
        return this.repository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id));
    }

    @PostMapping("api/artists/{id}/albums")
    public Album addAlbumForArtist(@PathVariable Long id, @RequestBody Album newAlbum) {
        return this.albumsService.addAlbumForArtist(id, newAlbum);
    }
}
