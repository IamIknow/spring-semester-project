package ru.omgups.courseproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omgups.courseproject.dtos.ReviewDto;
import ru.omgups.courseproject.entities.Album;
import ru.omgups.courseproject.entities.Review;
import ru.omgups.courseproject.entities.User;
import ru.omgups.courseproject.repositories.ReviewsRepository;

import java.util.Collection;

@Service
public class ReviewsService {
    private ReviewsRepository reviewsRepository;
    private AlbumsService albumsService;
    private UsersService usersService;

    @Autowired
    public ReviewsService(ReviewsRepository reviewsRepository, AlbumsService albumsService, UsersService usersService) {
        this.reviewsRepository = reviewsRepository;
        this.albumsService = albumsService;
        this.usersService = usersService;
    }

    public Collection<Review> getReviewsForAlbum(Long albumId) {
        Album album = albumsService.getAlbumById(albumId);

        return album.getReviews();
    }

    public Collection<Review> getReviewsForUser(Long userId) {
        User user = usersService.getUserById(userId);

        return user.getReviews();
    }

    public Review createReviewForAlbum(ReviewDto reviewDto, Long albumId, User user) {
        Album album = albumsService.getAlbumById(albumId);

        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setScore(reviewDto.getScore());
        review.setUser(user);
        review.setAlbum(album);

        return reviewsRepository.save(review);
    }
}
