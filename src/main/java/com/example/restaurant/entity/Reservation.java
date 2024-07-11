package com.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.restaurant.entity.interfaces.EntityInterface;
import com.example.restaurant.entity.interfaces.SluggerInterface;
import com.example.restaurant.json_views.JsonViewsReservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation implements EntityInterface, SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewsReservation.Id.class)
    private Long id;

    @JsonView(JsonViewsReservation.Slug.class)
    private String slug;

    @Override
    public String getField() {
        return "" + id;
    }
}