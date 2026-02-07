package com.personal.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.personal.demo.Entity.Destination;
import com.personal.demo.Service.MapService;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
    private final MapService mapService;
    private final GeoApiContext geoApiContext;

    public TravelController(MapService mapService, GeoApiContext geoApiContext) {
        this.mapService = mapService;
        this.geoApiContext = geoApiContext;
    }

    // GET endpoint: fetch all trips
    @GetMapping("/trips")
    public String getAllTrips() {
        return "List of all trips";
    }

    // GET endpoint: fetch a trip by ID
    @GetMapping("/trips/{id}")
    public String getTripById(@PathVariable Long id) {
        return "Trip details for ID: " + id;
    }

    // POST endpoint: create a new trip

@PostMapping("/trips")
public ResponseEntity<String> createTrip(@RequestBody Destination destination) {
    try {
        // 1. Get Geolocation details from Google Maps using the typed locationName
        GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, destination.getLocationName()).await();

        if (results != null && results.length > 0) {
            // 2. Extract data from the first result
            double lat = results[0].geometry.location.lat;
            double lng = results[0].geometry.location.lng;
            String fullAddress = results[0].formattedAddress;

            // 3. Set the data back into your object
            destination.setLatitude(lat);
            destination.setLongitude(lng);
            destination.setLocationName(fullAddress); // Update to the official address

            // Note: dateVisited and notes are already inside 'destination' from the @RequestBody

            // 4. (Optional) Save to your DB using Hibernate
            // destinationRepository.save(destination);

            return ResponseEntity.ok("Trip saved: " + fullAddress + 
                                   " | Date: " + destination.getDateVisited() + 
                                   " | Notes: " + destination.getNotes() + 
                                   " | Coordinates: " + lat + "," + lng);
        } else {
            return ResponseEntity.status(404).body("Location not found on Google Maps.");
        }
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error connecting to Google Maps: " + e.getMessage());
    }
}

    // PUT endpoint: update an existing trip
    @PutMapping("/trips/{id}")
    public String updateTrip(@PathVariable Long id, @RequestBody String trip) {
        return "Trip updated for ID: " + id + " with data: " + trip;
    }

    // DELETE endpoint: remove a trip
    @DeleteMapping("/trips/{id}")
    public String deleteTrip(@PathVariable Long id) {
        return "Trip deleted with ID: " + id;
    }
}