package com.example.restaurant.service;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.DTO.MenuDTO;
import com.example.restaurant.exception.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MenuService implements DAOServiceInterface<Menu> {

    private MenuRepository menuRepository;

    public List<Menu> findAll() {
        return this.menuRepository.findAll();
    }

    public Menu getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Menu getObjectById(Long id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        return optionalMenu.orElseThrow(() -> new NotFound("Menu", "id", id));
    }

    public Menu getObjectBySlug(String slug) {
        Optional<Menu> optionalMenu = menuRepository.findBySlug(slug);
        return optionalMenu.orElseThrow(() -> new NotFound("Menu", "slug", slug));
    }

    public Menu persist(MenuDTO menuDTO) {
        return persist(menuDTO, null);
    }

    public Menu persist(MenuDTO menuDTO, Long id) {
        Menu menu = new Menu();
        if (id != null) {
            menu = getObjectById(id);
        }

        return menuRepository.saveAndFlush(menu);
    }

    public MenuDTO getDTOById(Long id) {
        Menu menu = getObjectById(id);
        MenuDTO dto = new MenuDTO();
        // dto.setName(menu.getName());
        return dto;
    }
}
