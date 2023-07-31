package com.irby.jaden.resturantreview.user.model;

import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.user.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class userEntityTest {

    private UserEntity user;

    private UserEntity emptyUser;

    private Date date;

    private List<Review> reviews;

    @Mock
    private Review review1;

    @Mock
    private Review review2;

    @BeforeEach
    public void setUp() throws ParseException {
        emptyUser = new UserEntity();

        reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse("2022-01-15 12:30:45");
        user = new UserEntity("John", "Doe","JonDoe123", date,"Jon@Doe.com");
        user.setReviews(reviews);
    }

    @Test
    public void testContentToString() throws Exception {
        String expected ="UserEntity{" +
                "id=0"+
                ", firstName='John" +'\'' +
                ", lastName='Doe" + '\'' +
                ", userName='JonDoe123" +'\'' +
                ", createdAt=" +date  +
                ", email='Jon@Doe.com" + '\'' +
                ", reviews=" + reviews +
                '}';
        assertEquals(expected, user.toString());
    }


}
