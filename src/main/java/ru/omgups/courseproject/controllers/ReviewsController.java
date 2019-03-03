package ru.omgups.courseproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.omgups.courseproject.dtos.ReviewDto;
import ru.omgups.courseproject.entities.Album;
import ru.omgups.courseproject.entities.Review;
import ru.omgups.courseproject.services.ReviewsService;
import ru.omgups.courseproject.services.UsersService;

import java.util.Collection;

@RestController
public class ReviewsController {

    private ReviewsService reviewsService;
    private UsersService usersService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService, UsersService usersService) {
        this.reviewsService = reviewsService;
        this.usersService = usersService;
    }

    @GetMapping("api/albums/{albumId}/reviews")
    public Collection<Review> getReviewsForAlbum(@PathVariable Long albumId) {
        return reviewsService.getReviewsForAlbum(albumId);
    }

    @GetMapping("api/users/{userId}/reviews")
    public Collection<Review> getReviewsForUser(@PathVariable Long userId) {
        return reviewsService.getReviewsForUser(userId);
    }

    @PostMapping("api/albums/{albumId}/reviews")
    public Review createReview(@RequestBody ReviewDto reviewDto, @PathVariable Long albumId) {
        try {
            return reviewsService.createReviewForAlbum(reviewDto, albumId, usersService.getCurrentUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
