package com.irby.jaden.resturantreview.user.controller;

import com.irby.jaden.resturantreview.BaseControllerTest;
import com.irby.jaden.resturantreview.domain.core.user.controller.UserController;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import com.irby.jaden.resturantreview.domain.core.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private User mockResponseUser1;
    private User mockResponseUser2;

    private Date date;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse("2022-01-15 12:30:45");

        inputUser = new User("John", "Doe","JonDoe123", date,"Jon@Doe.com");

        mockResponseUser1 = new User();
        mockResponseUser1.setId(1);

        mockResponseUser2 = new User();
        mockResponseUser2.setId(2);
    }

    @Test
    public void createUserSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseUser1).when(mockUserService).createUser(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputUser)).
        );
    }

}
