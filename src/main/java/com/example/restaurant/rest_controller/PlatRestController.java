package com.example.restaurant.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.restaurant.entity.Plat;
import com.example.restaurant.DTO.PlatDTO;
import com.example.restaurant.service.PlatService;
import com.example.restaurant.json_views.JsonViews;
import com.example.restaurant.mapping.UrlRoute;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(UrlRoute.URL_API)
public class PlatRestController {

    private PlatService platService;

    @GetMapping(path = UrlRoute.URL_PLAT)
    @JsonView(JsonViews.PlatListJsonViews.class)
    public List<Plat> list() {
        return this.platService.findAll();
    }

    @GetMapping(path = UrlRoute.URL_PLAT + "/{field}")
    @JsonView(JsonViews.PlatShowJsonViews.class)
    public Plat show(@PathVariable String field) {
        return this.platService.getByField(field);
    }

    @PostMapping(path = UrlRoute.URL_PLAT_NEW)
    public Plat create(@Valid @RequestBody PlatDTO platDTO) {
        return platService.persist(platDTO);
    }

    @PutMapping(path = UrlRoute.URL_PLAT_EDIT + "/{id}")
    public Plat update(@Valid @RequestBody PlatDTO platDTO, @PathVariable Long id) {
        return platService.persist(platDTO, id);
    }

}