package ru.omgups.courseproject.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int score;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Album album;

    public Review(int score, String content, Album album, User user) {
        this.score = score;
        this.content = content;
        this.album = album;
        this.user = user;
    }

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return score == review.score &&
                Objects.equals(id, review.id) &&
                Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, content);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", score=" + score +
                ", content='" + content + '\'' +
                '}';
    }
}
