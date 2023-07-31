package com.irby.jaden.resturantreview.user.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.user.model.UserEntity;
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

    private UserEntity inputUser;
    private UserEntity mockResponseUser1;
    private UserEntity mockResponseUser2;

    private Date date;

    private List<Review> reviews;

    @Mock
    private Review review1;

    @Mock
    private Review review2;

    @BeforeEach
    public void setUp() throws ParseException {

        reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse("2022-01-15 12:30:45");

        inputUser = new UserEntity("John", "Doe","JonDoe123", date,"Jon@Doe.com");
        inputUser.setReviews(reviews);

        mockResponseUser1 = new UserEntity();
        mockResponseUser1.setReviews(reviews);
        mockResponseUser1.setId(1);

        mockResponseUser2 = new UserEntity();
        mockResponseUser2.setReviews(reviews);
        mockResponseUser2.setId(2);
    }

    @Test
    public void createUserTestSuccess(){
        BDDMockito.doReturn(mockResponseUser1).when(mockUserRepo).save(ArgumentMatchers.any());
        UserEntity returnedUser = userService.create(inputUser);
        Assertions.assertNotNull(returnedUser, "User Should not be null");
        Assertions.assertEquals(returnedUser.getId(), 1);
    }
    @Test
    public void getUserByIdSuccess() throws UserExecption {
        BDDMockito.doReturn(Optional.of(mockResponseUser1)).when(mockUserRepo).findById(1);
        UserEntity foundUser = userService.getUserById(1);
        Assertions.assertEquals(mockResponseUser1.toString(), foundUser.toString());

    }
    @Test
    public void getUserByIdFailed(){
        BDDMockito.doReturn(Optional.empty()).when(mockUserRepo).findById(1);
        Assertions.assertThrows(UserExecption.class, () ->{
            userService.getUserById(1);
        });

    }
    @Test
    public void getAllUserSuccess(){
        List<UserEntity> userList = new ArrayList<>();
        userList.add(mockResponseUser1);
        userList.add(mockResponseUser2);

        BDDMockito.doReturn(userList).when(mockUserRepo).findAll();

        List<UserEntity> responseUsers = userService.getAllUsers();
        Assertions.assertIterableEquals(userList, responseUsers);
    }
    @Test
    public void updateUserSuccess(){
        List<Review> reviewList = new ArrayList<>();

        reviewList.add(new Review("title","content", 1,1L,1L));
        reviewList.add(new Review("title2","content2", 5,1L,1L));

        UserEntity execptedUser = new UserEntity("Joe", "Boat", "example", 'com.exmaple' )
        BDDMockito.doReturn(Optional.of(mockResponseUser1)).when(mockUserRepo).findById(1);
        BDDMockito.doReturn()

    }
}
