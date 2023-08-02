package com.irby.jaden.resturantreview.domain.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;

public abstract class BaseController {
    protected static Long validateId(String id) throws BadRequestException {
        long reviewId;
        try {
            reviewId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid id format. Expected a Long value.");
        }
        return reviewId;

    }
}
