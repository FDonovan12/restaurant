package com.example.restaurant.controller;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.DTO.MenuDTO;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.mapping.UrlRoute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping(path = UrlRoute.URL_MENU)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("menu/index");
        mav.addObject("menus", menuService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_MENU + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Menu menu = menuService.getByField(field);

        mav.setViewName("menu/show");
        mav.addObject("menu", menu);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_MENU_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new MenuDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_MENU_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                menuService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_MENU_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("menu") MenuDTO menuDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, menuDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_MENU_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("menu") MenuDTO menuDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, menuDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, MenuDTO dto, String uri, boolean isEdit) {
        mav.setViewName("menu/form");
        mav.addObject("menu", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, MenuDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("menu/form");
            return mav;
        }
        menuService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_MENU);
        return mav;
    }

}
