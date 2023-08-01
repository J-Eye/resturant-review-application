package com.irby.jaden.resturantreview.user.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import com.irby.jaden.resturantreview.domain.core.user.repo.UserRepo;
import com.irby.jaden.resturantreview.domain.core.user.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo mockUserRepo;

    @InjectMocks
    private UserServiceImpl userService;

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
    public void createUserTestSuccess(){
        BDDMockito.doReturn(mockResponseUser1).when(mockUserRepo).save(ArgumentMatchers.any());
        User returnedUser = userService.createUser(inputUser);
        Assertions.assertNotNull(returnedUser, "User Should not be null");
        Assertions.assertEquals(returnedUser.getId(), 1);
    }
    @Test
    public void getUserByIdSuccess() throws UserExecption {
        BDDMockito.doReturn(Optional.of(mockResponseUser1)).when(mockUserRepo).findById(1L);
        User foundUser = userService.getUserById(1L);
        Assertions.assertEquals(mockResponseUser1.toString(), foundUser.toString());

    }
    @Test
    public void getUserByIdFailed(){
        BDDMockito.doReturn(Optional.empty()).when(mockUserRepo).findById(1L);
        Assertions.assertThrows(UserExecption.class, () ->{
            userService.getUserById(1L);
        });

    }
    @Test
    public void getAllUserSuccess(){
        List<User> userList = new ArrayList<>();
        userList.add(mockResponseUser1);
        userList.add(mockResponseUser2);

        BDDMockito.doReturn(userList).when(mockUserRepo).findAll();

        List<User> responseUsers = userService.getAllUsers();
        Assertions.assertIterableEquals(userList, responseUsers);
    }
    @Test
    public void updateUserSuccess() throws UserExecption {

        User execptedUser = new User("Joe", "Boat", "example", date, "J@example.com" );
        BDDMockito.doReturn(Optional.of(mockResponseUser1)).when(mockUserRepo).findById(1L);
        BDDMockito.doReturn(execptedUser).when(mockUserRepo).save(ArgumentMatchers.any());

        User actualUser = userService.updateUser(1L, execptedUser);
        Assertions.assertEquals(actualUser.toString(), execptedUser.toString());

    }

    @Test
    public void updateUserFail() {


        User execptedUser = new User("Joe", "Boat", "example", date, "J@example.com" );
        BDDMockito.doReturn(Optional.empty()).when(mockUserRepo).findById(1L);
        Assertions.assertThrows(UserExecption.class, () ->{
            userService.updateUser(1L, execptedUser);
        });
    }

    @Test
    public void deleteUserSuccess() throws UserExecption {
        BDDMockito.doReturn(Optional.of(mockResponseUser1)).when(mockUserRepo).findById(1L);
        Boolean actualResponse = userService.deleteUser(1L);
        Assertions.assertTrue(actualResponse);
    }

    @Test
    public void deleteUserFail(){
        BDDMockito.doReturn(Optional.empty()).when(mockUserRepo).findById(1L);
        Assertions.assertThrows(UserExecption.class, () -> {
            userService.deleteUser(1L);
        });
    }
}
