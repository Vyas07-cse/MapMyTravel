package com.personal.demo.Service;

import org.springframework.stereotype.Service;

import com.personal.demo.Entity.Photo;
import com.personal.demo.Repositories.PhotoRepo;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepo photoRepo;

    public PhotoService(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }

    public Photo addPhoto(Photo photo) {
        return photoRepo.save(photo);
    }

    public List<Photo> getPhotosByDestination(Long destinationId) {
        return photoRepo.findByDestinationId(destinationId);
    }

    public void deletePhoto(Long id) {
        photoRepo.deleteById(id);
    }
}