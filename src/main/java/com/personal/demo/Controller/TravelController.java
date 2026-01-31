package com.personal.demo.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

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
    public String createTrip(@RequestBody String trip) {
        return "Trip created: " + trip;
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