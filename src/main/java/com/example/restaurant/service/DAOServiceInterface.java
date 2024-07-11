package com.example.restaurant.service;

import com.example.restaurant.exception.NotFoundRestaurantException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DAOServiceInterface<T> {

    List<T> findAll();

    T getObjectById(Long id);

}
