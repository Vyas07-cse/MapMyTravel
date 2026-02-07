package com.personal.demo.Service;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

@Service
public class MapService {

    private final GeoApiContext geoApiContext;

    public MapService(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    public GeocodingResult[] getCoordinates(String destination) throws Exception {
        return GeocodingApi.geocode(geoApiContext, destination).await();
    }
}