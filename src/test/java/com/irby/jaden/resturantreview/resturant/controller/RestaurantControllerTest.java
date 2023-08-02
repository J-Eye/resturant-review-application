package com.irby.jaden.resturantreview.resturant.controller;

import com.irby.jaden.resturantreview.BaseControllerTest;
import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantNotFoundException;
import com.irby.jaden.resturantreview.domain.core.resturant.controller.RestaurantController;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.resturant.service.RestaurantService;
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

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class RestaurantControllerTest extends BaseControllerTest {

    @MockBean
    private RestaurantService mockRestaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @Autowired
    private MockMvc mockMvc;

    private Restaurant inputRestaurant;
    private Restaurant mockResponseRestaurant1;
    private Restaurant mockResponseRestaurant2;

    @BeforeEach
    public void setUp() {
        inputRestaurant = new Restaurant("Delicious Bites", "123 Main Street");
        inputRestaurant.setId(1L);

        mockResponseRestaurant1 = new Restaurant();
        mockResponseRestaurant1.setId(1L);
        mockResponseRestaurant2 = new Restaurant();
        mockResponseRestaurant2.setId(2L);
    }

    @Test
    public void createRestaurantSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseRestaurant1).when(mockRestaurantService).createRestaurant(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputRestaurant)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    public void getRestaurantByIdTestSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseRestaurant1).when(mockRestaurantService).getRestaurantById(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/restaurant/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getRestaurantByIdTestNotFound() throws Exception {
        BDDMockito.doThrow(new ResturantNotFoundException("not found")).when(mockRestaurantService).getRestaurantById(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/restaurant/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void updateRestaurantSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseRestaurant1).when(mockRestaurantService).updateRestaurant(ArgumentMatchers.any(), ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.put("/restaurant/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputRestaurant)))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateRestaurantNotFound() throws Exception {
        BDDMockito.doThrow(new ResturantNotFoundException("not found")).when(mockRestaurantService).updateRestaurant(ArgumentMatchers.any(), ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.put("/restaurant/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputRestaurant)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteRestaurantSuccess() throws Exception {
        BDDMockito.doReturn(true).when(mockRestaurantService).deleteRestaurant(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/restaurant/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteRestaurantTestNotFound() throws Exception{
        BDDMockito.doThrow(new ResturantNotFoundException("Not Found")).when(mockRestaurantService).deleteRestaurant(ArgumentMatchers.any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/restaurant/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
