package ru.omgups.courseproject.controllers;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import ru.omgups.courseproject.entities.Album;
import ru.omgups.courseproject.entities.Artist;
import ru.omgups.courseproject.repositories.AlbumsRepository;
import ru.omgups.courseproject.repositories.ArtistsRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AlbumsRepository albumsRepository;

    @Autowired
    private ArtistsRepository artistsRepository;

    private MockMvc mockMvc;
    private Gson gson = new Gson();
    private Artist artist;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        this.artist = new Artist("Artist");
        artistsRepository.save(artist);

        Album mock2 = new Album();
        mock2.setArtist(artist);
        mock2.setName("Album2");
        Album mock3 = new Album();
        mock3.setArtist(artist);
        mock3.setName("Album3");

        albumsRepository.save(mock2);
        albumsRepository.save(mock3);
    }

    @Test
    @WithMockUser
    public void addAlbumForArtist() throws Exception {
        Artist artist = artistsRepository.save(new Artist("Name"));
        Album album = new Album();
        album.setName("Album");

        ResultActions perform = mockMvc.perform(post("/api/artists/" + artist.getId() + "/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(album)));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Album")));
    }

    @Test
    @WithMockUser
    public void getAlbumsTest() throws Exception {
        ResultActions perform = mockMvc.perform(get("/api/albums"));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is("Album2")));
    }

    @Test
    @WithMockUser
    public void getAlbumByIdTest() throws Exception {
        Album mock1 = new Album();
        mock1.setArtist(artist);
        mock1.setName("Album1");
        albumsRepository.save(mock1);

        ResultActions perform = mockMvc.perform(get("/api/albums/" + mock1.getId()));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Album1")));
    }
}