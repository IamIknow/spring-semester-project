package ru.omgups.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omgups.courseproject.entities.Album;

public interface AlbumsRepository extends JpaRepository<Album, Long> {
}
