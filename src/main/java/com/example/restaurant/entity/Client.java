package com.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.restaurant.entity.interfaces.EntityInterface;
import com.example.restaurant.entity.interfaces.SluggerInterface;
import com.example.restaurant.json_views.JsonViewsClient;
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
public class Client implements EntityInterface, SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewsClient.Id.class)
    private Long id;

    @OneToMany(mappedBy = "client")
    @JsonView(JsonViewsClient.Plats.class)
    private List<Plat> plats = new ArrayList<>();

    @JsonView(JsonViewsClient.Slug.class)
    private String slug;


    @Override
    public String getField() {
        return "" + id;
    }
}