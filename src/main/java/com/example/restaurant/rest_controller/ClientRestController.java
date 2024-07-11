package com.example.restaurant.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.restaurant.entity.Client;
import com.example.restaurant.DTO.ClientDTO;
import com.example.restaurant.service.ClientService;
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
public class ClientRestController {

    private ClientService clientService;

    @GetMapping(path = UrlRoute.URL_CLIENT)
    @JsonView(JsonViews.ClientListJsonViews.class)
    public List<Client> list() {
        return this.clientService.findAll();
    }

    @GetMapping(path = UrlRoute.URL_CLIENT + "/{field}")
    @JsonView(JsonViews.ClientShowJsonViews.class)
    public Client show(@PathVariable String field) {
        return this.clientService.getByField(field);
    }

    @PostMapping(path = UrlRoute.URL_CLIENT_NEW)
    public Client create(@Valid @RequestBody ClientDTO clientDTO) {
        return clientService.persist(clientDTO);
    }

    @PutMapping(path = UrlRoute.URL_CLIENT_EDIT + "/{id}")
    public Client update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id) {
        return clientService.persist(clientDTO, id);
    }

}