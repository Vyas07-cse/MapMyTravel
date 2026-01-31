package com.personal.demo.Service;

import com.personal.demo.Entity.Destination;

import org.springframework.stereotype.Service;

import java.util.List;

import com.personal.demo.Repositories.DestinationRepo;

@Service
public class DestinationService {
    private final DestinationRepo destRepo;

    public DestinationService(DestinationRepo destRepo) {
        this.destRepo = destRepo;
    }

    public Destination addDestination(Destination destination) {
        return destRepo.save(destination);
    }

    public List<Destination> getDestinationsByUser(Long userId) {
        return destRepo.findByUserId(userId);
    }

    public Destination updateDestination(Long id, Destination updated) {
        Destination existing = destRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
        existing.setLocationName(updated.getLocationName());
        existing.setLatitude(updated.getLatitude());
        existing.setLongitude(updated.getLongitude());
        existing.setDateVisited(updated.getDateVisited());
        existing.setNotes(updated.getNotes());
        return destRepo.save(existing);
    }

    public void deleteDestination(Long id) {
        destRepo.deleteById(id);
    }
}