package ru.omgups.courseproject.services;

import org.springframework.stereotype.Service;
import ru.omgups.courseproject.entities.Album;
import ru.omgups.courseproject.entities.Artist;
import ru.omgups.courseproject.exceptions.AlbumNotFoundException;
import ru.omgups.courseproject.exceptions.ArtistNotFoundException;
import ru.omgups.courseproject.repositories.AlbumsRepository;
import ru.omgups.courseproject.repositories.ArtistsRepository;

import java.util.List;

@Service
public class AlbumsService {
    private final ArtistsRepository artistsRepository;
    private final AlbumsRepository albumsRepository;

    AlbumsService(ArtistsRepository artistsRepository, AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
        this.artistsRepository = artistsRepository;
    }

    public Album addAlbumForArtist(Long artistId, Album album) {
        Artist artist = this.artistsRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
        album.setArtist(artist);

        return albumsRepository.save(album);
    }

    public Album getAlbumById(Long albumId) {
        return albumsRepository.findById(albumId).orElseThrow(() -> new ArtistNotFoundException(albumId));
    }

    public List<Album> getAlbumsForArtist(Long artistId) {
        Artist artist = this.artistsRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
        return this.albumsRepository.findByArtist(artist);
    }

    public Album deleteAlbumById(Long id) {
        Album album = albumsRepository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id));
        albumsRepository.delete(album);

        return album;
    }
}
