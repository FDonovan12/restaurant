package com.example.restaurant.service;

import com.example.restaurant.entity.Plat;
import com.example.restaurant.repository.PlatRepository;
import com.example.restaurant.DTO.PlatDTO;
import com.example.restaurant.exception.NotFoundRestaurantException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlatService implements DAOServiceInterface<Plat> {

    private PlatRepository platRepository;

    public List<Plat> findAll() {
        return this.platRepository.findAll();
    }

    public Plat getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Plat getObjectById(Long id) {
        Optional<Plat> optionalPlat = platRepository.findById(id);
        return optionalPlat.orElseThrow(() -> new NotFoundRestaurantException("Plat", "id", id));
    }

    public Plat getObjectBySlug(String slug) {
        Optional<Plat> optionalPlat = platRepository.findBySlug(slug);
        return optionalPlat.orElseThrow(() -> new NotFoundRestaurantException("Plat", "slug", slug));
    }

    public Plat persist(PlatDTO platDTO) {
        return persist(platDTO, null);
    }

    public Plat persist(PlatDTO platDTO, Long id) {
        Plat plat = new Plat();
        if (id != null) {
            plat = getObjectById(id);
        }

        plat.setName(platDTO.getName());
        plat.setDescription(platDTO.getDescription());
        plat.setPrix(platDTO.getPrix());

        return platRepository.saveAndFlush(plat);
    }

    public PlatDTO getDTOById(Long id) {
        Plat plat = getObjectById(id);
        PlatDTO dto = new PlatDTO();
        dto.setName(plat.getName());
        return dto;
    }
}
