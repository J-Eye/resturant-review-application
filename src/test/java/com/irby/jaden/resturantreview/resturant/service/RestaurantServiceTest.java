package com.irby.jaden.resturantreview.resturant.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantNotFoundException;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserNotFoundException;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.resturant.repo.ResturantRepo;
import com.irby.jaden.resturantreview.domain.core.resturant.service.RestaurantServiceImpl;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private ResturantRepo mockRestaurantRepo;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private Restaurant inputRestaurant;
    private Restaurant mockResponseRestaurant1;
    private Restaurant mockResponseRestaurant2;

    @BeforeEach
    public void setUp(){

        inputRestaurant = new Restaurant("Delicious Bites", "123 Main Street");
        inputRestaurant.setId(1L);

        mockResponseRestaurant1 = new Restaurant();
        mockResponseRestaurant1.setId(1L);
        mockResponseRestaurant2 = new Restaurant();
        mockResponseRestaurant2.setId(2L);
    }

    @Test
    public void createRestaurantTestSuccess() throws BadRequestException {
        BDDMockito.doReturn(mockResponseRestaurant1).when(mockRestaurantRepo).save(ArgumentMatchers.any());
        Restaurant returnedRestaurant = restaurantService.createRestaurant(inputRestaurant);
        Assertions.assertNotNull(returnedRestaurant, "Restaurant Should not be null");
        Assertions.assertEquals(1L, returnedRestaurant.getId());
    }
    @Test
    public void getUserByRestaurantSuccess() throws ResturantNotFoundException {
        BDDMockito.doReturn(Optional.of(mockResponseRestaurant1)).when(mockRestaurantRepo).findById(1L);
        Restaurant foundRestaurant = restaurantService.getRestaurantById(1L);
        Assertions.assertEquals(mockResponseRestaurant1.toString(), foundRestaurant.toString());

    }
    @Test
    public void getRestaurantByIdFailed(){
        BDDMockito.doReturn(Optional.empty()).when(mockRestaurantRepo).findById(1L);
        Assertions.assertThrows(ResturantNotFoundException.class, () ->{
            restaurantService.getRestaurantById(1L);
        });

    }
    @Test
    public void getAllRestaurantSuccess(){
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(mockResponseRestaurant1);
        restaurantList.add(mockResponseRestaurant2);

        BDDMockito.doReturn(restaurantList).when(mockRestaurantRepo).findAll();

        List<Restaurant> responseRestaurants = restaurantService.getAllRestaurants();
        Assertions.assertIterableEquals(responseRestaurants, responseRestaurants);
    }
    @Test
    public void updateRestaurantSuccess() throws BadRequestException, ResturantNotFoundException {

        Restaurant execptedRestaurant = new Restaurant("mcLovin", "123 Go Love Yourself");
        BDDMockito.doReturn(Optional.of(mockResponseRestaurant1)).when(mockRestaurantRepo).findById(1L);
        BDDMockito.doReturn(execptedRestaurant).when(mockRestaurantRepo).save(ArgumentMatchers.any());

        Restaurant actualRestaurant = restaurantService.updateRestaurant(1L, execptedRestaurant);
        Assertions.assertEquals(actualRestaurant.toString(), execptedRestaurant.toString());

    }

    @Test
    public void updateRestaurantFail() {

        Restaurant execptedRestaurant = new Restaurant("mcLovin", "123 Go Love Yourself" );
        BDDMockito.doReturn(Optional.empty()).when(mockRestaurantRepo).findById(1L);
        Assertions.assertThrows(ResturantNotFoundException.class, () ->{
            restaurantService.updateRestaurant(1L, execptedRestaurant);
        });
    }

    @Test
    public void deleteRestaurantSuccess() throws ResturantNotFoundException {
        BDDMockito.doReturn(Optional.of(mockResponseRestaurant1)).when(mockRestaurantRepo).findById(1L);
        Boolean actualResponse = restaurantService.deleteRestaurant(1L);
        Assertions.assertTrue(actualResponse);
    }

    @Test
    public void deleteRestaurantFail(){
        BDDMockito.doReturn(Optional.empty()).when(mockRestaurantRepo).findById(1L);
        Assertions.assertThrows(ResturantNotFoundException.class, () -> {
            restaurantService.deleteRestaurant(1L);
        });
    }
}
