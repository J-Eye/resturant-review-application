package com.irby.jaden.resturantreview.review.controller;

import com.irby.jaden.resturantreview.BaseControllerTest;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewNotFoundExecption;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.review.controller.ReviewController;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.service.ReviewService;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ReviewControllerTest extends BaseControllerTest {

    @MockBean
    private ReviewService mockReviewService;

    @Mock
    private Restaurant mockRestaurant;

    @Mock
    private User mockUser;

    @InjectMocks
    private ReviewController reviewController;

    @Autowired
    private MockMvc mockMvc;

    private Review inputReview;
    private Review mockResponseReview;

    @BeforeEach
    public void setUp(){
        mockRestaurant = new Restaurant();
        mockUser = new User();
        inputReview = new Review("Meh", "eh", 3, mockRestaurant, mockUser);

        mockResponseReview = new Review();
        mockResponseReview.setId(1L);
    }

    @Test
    public void createReviewSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseReview).when(mockReviewService).createReview(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputReview)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    public void getReviewByIdTestSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseReview).when(mockReviewService).getReviewById(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/review/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getReviewByIdTestNotFound() throws Exception {
        BDDMockito.doThrow(new ReviewNotFoundExecption("not found")).when(mockReviewService).getReviewById(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/review/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void updateReviewSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseReview).when(mockReviewService).updateReview(ArgumentMatchers.any(), ArgumentMatchers.any());
        inputReview.setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/review/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputReview)))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateReviewNotFound() throws Exception {
        BDDMockito.doThrow(new ReviewNotFoundExecption("not found")).when(mockReviewService).updateReview(ArgumentMatchers.any(), ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.put("/review/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputReview)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteReviewSuccess() throws Exception {
        BDDMockito.doReturn(true).when(mockReviewService).deleteReview(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/review/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteReviewTestNotFound() throws Exception{
        BDDMockito.doThrow(new ReviewNotFoundExecption("Not Found")).when(mockReviewService).deleteReview(ArgumentMatchers.any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/review/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
