package ru.omgups.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omgups.courseproject.entities.Album;
import ru.omgups.courseproject.entities.Artist;

import java.util.List;

public interface AlbumsRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtist(Artist artist);
}
