package com.irby.jaden.resturantreview;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseControllerTest {
    protected static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
