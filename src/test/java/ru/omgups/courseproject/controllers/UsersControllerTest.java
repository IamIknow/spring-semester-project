package ru.omgups.courseproject.controllers;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import ru.omgups.courseproject.entities.User;
import ru.omgups.courseproject.repositories.UsersRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UsersRepository repository;

    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        User mock1 = new User("John", "Doe", "email", "123");
        User mock2 = new User("Ivan", "Ivanov", "email", "123");

        repository.save(mock1);
        repository.save(mock2);
    }

    @Test
    public void createUserTest() throws Exception {
        User testUser = new User("John", "Doe", "email", "123");
        ResultActions perform = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(testUser)));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")));
    }

    @Test
    public void getUsersTest() throws Exception {
        ResultActions perform = mockMvc.perform(get("/users"));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName", is("John")))
                .andExpect(jsonPath("$.[1].firstName", is("Ivan")));
    }
}
