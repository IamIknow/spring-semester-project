package ru.omgups.courseproject.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String genre;

    private Date releaseDate;

    private String recordsLabel;

    private String description;

    private String coverUrl;

    @ManyToOne
    @JoinColumn
    private Artist artist;

    public Album(String name, String genre, Date releaseDate, String recordsLabel, Artist artist, String description, String coverUrl) {
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", recordsLabel='" + recordsLabel + '\'' +
                '}';
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
