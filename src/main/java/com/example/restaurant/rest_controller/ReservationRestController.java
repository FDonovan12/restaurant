package com.example.restaurant.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.restaurant.entity.Reservation;
import com.example.restaurant.DTO.ReservationDTO;
import com.example.restaurant.service.ReservationService;
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
public class ReservationRestController {

    private ReservationService reservationService;

    @GetMapping(path = UrlRoute.URL_RESERVATION)
    @JsonView(JsonViews.ReservationListJsonViews.class)
    public List<Reservation> list() {
        return this.reservationService.findAll();
    }

    @GetMapping(path = UrlRoute.URL_RESERVATION + "/{field}")
    @JsonView(JsonViews.ReservationShowJsonViews.class)
    public Reservation show(@PathVariable String field) {
        return this.reservationService.getByField(field);
    }

    @PostMapping(path = UrlRoute.URL_RESERVATION_NEW)
    public Reservation create(@Valid @RequestBody ReservationDTO reservationDTO) {
        return reservationService.persist(reservationDTO);
    }

    @PutMapping(path = UrlRoute.URL_RESERVATION_EDIT + "/{id}")
    public Reservation update(@Valid @RequestBody ReservationDTO reservationDTO, @PathVariable Long id) {
        return reservationService.persist(reservationDTO, id);
    }

}