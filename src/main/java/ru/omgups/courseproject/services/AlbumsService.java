package ru.omgups.courseproject.services;

import org.springframework.stereotype.Service;
import ru.omgups.courseproject.entities.Album;
import ru.omgups.courseproject.entities.Artist;
import ru.omgups.courseproject.exceptions.ArtistNotFoundException;
import ru.omgups.courseproject.repositories.AlbumsRepository;
import ru.omgups.courseproject.repositories.ArtistsRepository;

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
}
