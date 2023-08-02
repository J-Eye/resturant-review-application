package com.irby.jaden.resturantreview.review.model;

import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {

    @Mock
    private Restaurant mockRestaurant;

    @Mock
    private User mockUser;

    private Review review;

    @BeforeEach
    public void setUp(){
        mockRestaurant = new Restaurant();
        mockUser = new User();
        review = new Review("Meh", "eh", 3, mockRestaurant, mockUser);
        review.setId(1L);
    }

    @Test
    public void testContentToString(){
        String expected = "Review{" +
                "id=1" +
                ", title='Meh'" +
                ", content='eh'" +
                ", rating=3" +
                ", restaurant=" + mockRestaurant +
                ", user=" + mockUser +
                '}';
        assertEquals(expected, review.toString());
    }

}
