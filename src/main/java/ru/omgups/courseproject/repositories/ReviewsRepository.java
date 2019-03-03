package ru.omgups.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omgups.courseproject.entities.Review;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
}
