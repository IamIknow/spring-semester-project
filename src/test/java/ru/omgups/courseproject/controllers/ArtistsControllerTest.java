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
import ru.omgups.courseproject.entities.Artist;
import ru.omgups.courseproject.exceptions.ArtistNotFoundException;
import ru.omgups.courseproject.repositories.ArtistsRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ArtistsRepository repository;

    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        Artist mock1 = new Artist("Artist1");
        Artist mock2 = new Artist("Artist2");
        Artist mock3 = new Artist("Artist3");

        repository.save(mock1);
        repository.save(mock2);
        repository.save(mock3);
    }

    @Test
    @WithMockUser
    public void createArtistTest() throws Exception {
        Artist artist = new Artist("Name");
        ResultActions perform = mockMvc.perform(post("/api/artists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(artist)));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Name")));
    }

    @Test
    @WithMockUser
    public void getArtistsTest() throws Exception {
        ResultActions perform = mockMvc.perform(get("/api/artists"));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is("Artist")));
    }

    @Test
    @WithMockUser
    public void getArtistByIdTest() throws Exception {
        ResultActions perform = mockMvc.perform(get("/api/artists/1"));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Artist")));
    }

    @Test
    @WithMockUser
    public void editArtist() throws Exception {
        Artist newArtist = new Artist("newName");

        ResultActions perform = mockMvc.perform(put("/api/artists/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(newArtist)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("newName")));
    }

    @Test(expected = ArtistNotFoundException.class)
    @WithMockUser
    public void deleteUserTest() throws Exception {
        repository.save(new Artist("name"));
        ResultActions perform = mockMvc.perform(delete("/api/artists/4"));
        perform.andExpect(status().isOk());
        repository.findById(4L).orElseThrow(() -> new ArtistNotFoundException(4L));
    }
}
