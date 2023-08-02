package com.irby.jaden.resturantreview.resturant.model;

import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantTest {

    @Mock
    private Review mockReview1;

    @Mock
    private Review mockReview2;

    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        mockReview1 = new Review();
        mockReview2 = new Review();
        List<Review> reviews = new ArrayList<>();
        reviews.add(mockReview1);
        reviews.add(mockReview2);
        restaurant = new Restaurant("Delicious Bites", "123 Main Street");
        restaurant.setId(1L);
        restaurant.setReviews(reviews);
    }

    @Test
    public void testToString() {
        String expected = "Restaurant{" +
                "id=1" +
                ", name='Delicious Bites'" +
                ", address='123 Main Street'" +
                ", reviews=[" + mockReview1 +
                ", " + mockReview2 +
                "]}";
        assertEquals(expected, restaurant.toString());
    }
}

