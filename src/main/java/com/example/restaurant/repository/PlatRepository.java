package com.example.restaurant.repository;

import com.example.restaurant.entity.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {

    Optional<Plat> findBySlug(String slug);

}