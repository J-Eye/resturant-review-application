package com.irby.jaden.resturantreview.review.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewNotFoundExecption;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserNotFoundException;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.repo.ReviewRepo;
import com.irby.jaden.resturantreview.domain.core.review.service.ReviewServiceImpl;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepo mockReviewRepo;

    @Mock
    private Restaurant mockRestaurant;

    @Mock
    private User mockUser;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review inputReview;
    private Review mockResponseReview;

    @BeforeEach()
    public void setUp(){

        inputReview = new Review("Meh", "eh", 3, mockRestaurant, mockUser);

        mockResponseReview = new Review();
        mockResponseReview.setId(1L);
    }

    @Test
    public void createReviewTestSuccess() throws BadRequestException {
        BDDMockito.doReturn(mockResponseReview).when(mockReviewRepo).save(ArgumentMatchers.any());
        Review returnedReview = reviewService.createReview(inputReview);
        Assertions.assertNotNull(returnedReview, "Review Should not be null");
        Assertions.assertEquals(returnedReview.getId(), 1L);
    }
    @Test
    public void getReviewByIdSuccess() throws ReviewNotFoundExecption {
        BDDMockito.doReturn(Optional.of(mockResponseReview)).when(mockReviewRepo).findById(1L);
        Review foundReview = reviewService.getReviewById(1L);
        Assertions.assertEquals(mockResponseReview.toString(), foundReview.toString());

    }
    @Test
    public void getReviewByIdFailed(){
        BDDMockito.doReturn(Optional.empty()).when(mockReviewRepo).findById(1L);
        Assertions.assertThrows(ReviewNotFoundExecption.class, () ->{
            reviewService.getReviewById(1L);
        });

    }
    @Test
    public void updateReviewSuccess() throws ReviewNotFoundExecption, BadRequestException {

        Review expectedReview = new Review("WOW", "OMG", 5, mockRestaurant, mockUser);
        BDDMockito.doReturn(Optional.of(mockResponseReview)).when(mockReviewRepo).findById(1L);
        BDDMockito.doReturn(expectedReview).when(mockReviewRepo).save(ArgumentMatchers.any());

        Review actualReview = reviewService.updateReview(1L, expectedReview);
        Assertions.assertEquals(actualReview.toString(), expectedReview.toString());

    }

    @Test
    public void updateReviewFail() {


        Review expectedReview = new Review("WOW", "OMG", 5, mockRestaurant, mockUser);
        BDDMockito.doReturn(Optional.empty()).when(mockReviewRepo).findById(1L);
        Assertions.assertThrows(ReviewNotFoundExecption.class, () ->{
            reviewService.updateReview(1L, expectedReview);
        });
    }

    @Test
    public void deleteReviewSuccess() throws UserNotFoundException, ReviewNotFoundExecption {
        BDDMockito.doReturn(Optional.of(mockResponseReview)).when(mockReviewRepo).findById(1L);
        Boolean actualResponse = reviewService.deleteReview(1L);
        Assertions.assertTrue(actualResponse);
    }

    @Test
    public void deleteReviewFail(){
        BDDMockito.doReturn(Optional.empty()).when(mockReviewRepo).findById(1L);
        Assertions.assertThrows(ReviewNotFoundExecption.class, () -> {
            reviewService.deleteReview(1L);
        });
    }

}
