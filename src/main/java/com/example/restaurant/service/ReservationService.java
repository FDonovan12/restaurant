package com.example.restaurant.service;

import com.example.restaurant.entity.Reservation;
import com.example.restaurant.repository.ReservationRepository;
import com.example.restaurant.DTO.ReservationDTO;
import com.example.restaurant.exception.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationService implements DAOServiceInterface<Reservation> {

    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return this.reservationRepository.findAll();
    }

    public Reservation getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Reservation getObjectById(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        return optionalReservation.orElseThrow(() -> new NotFound("Reservation", "id", id));
    }

    public Reservation getObjectBySlug(String slug) {
        Optional<Reservation> optionalReservation = reservationRepository.findBySlug(slug);
        return optionalReservation.orElseThrow(() -> new NotFound("Reservation", "slug", slug));
    }

    public Reservation persist(ReservationDTO reservationDTO) {
        return persist(reservationDTO, null);
    }

    public Reservation persist(ReservationDTO reservationDTO, Long id) {
        Reservation reservation = new Reservation();
        if (id != null) {
            reservation = getObjectById(id);
        }

        return reservationRepository.saveAndFlush(reservation);
    }

    public ReservationDTO getDTOById(Long id) {
        Reservation reservation = getObjectById(id);
        ReservationDTO dto = new ReservationDTO();
        // dto.setName(reservation.getName());
        return dto;
    }
}
