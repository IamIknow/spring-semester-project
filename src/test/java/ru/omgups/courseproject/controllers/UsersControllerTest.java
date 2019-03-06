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
import ru.omgups.courseproject.dtos.UserDto;
import ru.omgups.courseproject.entities.User;
import ru.omgups.courseproject.repositories.UsersRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        User mock1 = new User("John", "Doe", "email1@mail.com", "123");
        User mock2 = new User("Ivan", "Ivanov", "email2@mail.com", "123");

        repository.save(mock1);
        repository.save(mock2);
    }


    @Test
    public void registerUserTest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("email@mail.com");
        userDto.setPassword("pass");
        userDto.setMatchingPassword("pass");

        ResultActions perform = mockMvc.perform(post("/api/users/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userDto)));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.email", is("email@mail.com")))
                .andExpect(jsonPath("$.password", nullValue()));
    }

    @Test
    @WithMockUser
    public void getUsersTest() throws Exception {
        ResultActions perform = mockMvc.perform(get("/api/users"));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName", is("John")))
                .andExpect(jsonPath("$.[1].firstName", is("Ivan")));
    }

    @Test
    @WithMockUser
    public void getUserByIdTest() throws Exception {
        ResultActions perform = mockMvc.perform(get("/api/users/1"));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.email", is("email1@mail.com")));
    }

    @Test
    @WithMockUser
    public void updateUserTest() throws Exception {
        repository.save(new User("John", "Doe", "mail@mail.com", "pass"));

        User updated = new User("Steve", "Doe", "email3@mail.com", "pass");

        ResultActions perform = mockMvc.perform(put("/api/users/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(updated)));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Steve")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.email", is("email3@mail.com")));
    }
}
