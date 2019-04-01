package ru.omgups.courseproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties("reviews")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String genre;

    private String releaseDate;

    private String recordsLabel;

    private String description;

    private String coverUrl;

    @ManyToOne
    @JoinColumn
    private Artist artist;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Album(String name, String genre, String releaseDate, String recordsLabel, Artist artist, String description, String coverUrl) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.recordsLabel = recordsLabel;
        this.artist = artist;
        this.description = description;
        this.coverUrl = coverUrl;
    }

    public Album() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRecordsLabel() {
        return recordsLabel;
    }

    public void setRecordsLabel(String recordsLabel) {
        this.recordsLabel = recordsLabel;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(name, album.name) &&
                Objects.equals(genre, album.genre) &&
                Objects.equals(releaseDate, album.releaseDate) &&
                Objects.equals(recordsLabel, album.recordsLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, releaseDate, recordsLabel);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", recordsLabel='" + recordsLabel + '\'' +
                ", description='" + description + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", artist=" + artist +
                ", reviews=" + reviews +
                '}';
    }
}
