package com.personal.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.demo.Entity.Destination;

@Repository
public interface DestinationRepo extends JpaRepository<Destination, Long> {

    List<Destination> findByUserId(Long userId);

}
