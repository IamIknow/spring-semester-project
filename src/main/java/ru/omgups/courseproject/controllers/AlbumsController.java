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

    @GetMapping("api/albums")
    public List<Album> getAlbums() {
        return this.repository.findAll();
    }

    @GetMapping("api/albums/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id));
    }

    @PostMapping("api/artists/{id}/albums")
    public Album addAlbumForArtist(@PathVariable Long id, @RequestBody Album newAlbum) {
        return this.albumsService.addAlbumForArtist(id, newAlbum);
    }

    @GetMapping("api/artists/{id}/albums")
    public List<Album> getAlbumsForArtist(@PathVariable Long id) {
        return this.albumsService.getAlbumsForArtist(id);
    }

    @DeleteMapping("api/albums/{id}")
    public Album deleteAlbum(@PathVariable Long id) {
        return this.albumsService.deleteAlbumById(id);
    }
}
