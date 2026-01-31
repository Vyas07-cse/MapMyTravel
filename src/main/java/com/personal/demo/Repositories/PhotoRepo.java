package com.personal.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.demo.Entity.Photo;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Long> {

    public List<Photo> findByDestinationId(Long destinationId);

}
