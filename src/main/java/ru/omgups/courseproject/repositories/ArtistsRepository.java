package ru.omgups.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omgups.courseproject.entities.Artist;

public interface ArtistsRepository extends JpaRepository<Artist, Long> {

}
