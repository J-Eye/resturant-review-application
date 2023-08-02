package com.irby.jaden.resturantreview.user.model;

import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTest {

    private User user;

    private Date date;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse("2022-01-15 12:30:45");
        user = new User("John", "Doe","JonDoe123", date,"Jon@Doe.com");
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
                '}';
        assertEquals(expected, user.toString());
    }


}
