package com.irby.jaden.resturantreview.user.controller;

import com.irby.jaden.resturantreview.BaseControllerTest;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserNotFoundException;
import com.irby.jaden.resturantreview.domain.core.user.controller.UserController;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import com.irby.jaden.resturantreview.domain.core.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest extends BaseControllerTest {

    @MockBean
    private UserService mockUserService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    private User inputUser;
    private User mockResponseUser;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2022-01-15 12:30:45");

        inputUser = new User("John", "Doe","JonDoe123", date,"Jon@Doe.com");

        mockResponseUser = new User();
        mockResponseUser.setId(1);
    }

    @Test
    public void createUserSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseUser).when(mockUserService).createUser(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputUser)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    public void getUsersByIdTestSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseUser).when(mockUserService).getUserById(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getUsersByIdTestNotFound() throws Exception {
        BDDMockito.doThrow(new UserNotFoundException("not found")).when(mockUserService).getUserById(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void updateUserSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseUser).when(mockUserService).updateUser(ArgumentMatchers.any(), ArgumentMatchers.any());
        inputUser.setId(1);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputUser)))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateUserNotFound() throws Exception {
        BDDMockito.doThrow(new UserNotFoundException("not found")).when(mockUserService).updateUser(ArgumentMatchers.any(), ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputUser)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteUserSuccess() throws Exception {
        BDDMockito.doReturn(true).when(mockUserService).deleteUser(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteUserTestNotFound() throws Exception{
        BDDMockito.doThrow(new UserNotFoundException("Not Found")).when(mockUserService).deleteUser(ArgumentMatchers.any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
