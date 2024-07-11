package com.example.restaurant.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.DTO.MenuDTO;
import com.example.restaurant.service.MenuService;
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
public class MenuRestController {

    private MenuService menuService;

    @GetMapping(path = UrlRoute.URL_MENU)
    @JsonView(JsonViews.MenuListJsonViews.class)
    public List<Menu> list() {
        return this.menuService.findAll();
    }

    @GetMapping(path = UrlRoute.URL_MENU + "/{field}")
    @JsonView(JsonViews.MenuShowJsonViews.class)
    public Menu show(@PathVariable String field) {
        return this.menuService.getByField(field);
    }

    @PostMapping(path = UrlRoute.URL_MENU_NEW)
    public Menu create(@Valid @RequestBody MenuDTO menuDTO) {
        return menuService.persist(menuDTO);
    }

    @PutMapping(path = UrlRoute.URL_MENU_EDIT + "/{id}")
    public Menu update(@Valid @RequestBody MenuDTO menuDTO, @PathVariable Long id) {
        return menuService.persist(menuDTO, id);
    }

}