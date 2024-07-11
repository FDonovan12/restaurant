package com.example.restaurant.controller;

import com.example.restaurant.entity.Plat;
import com.example.restaurant.DTO.PlatDTO;
import com.example.restaurant.service.PlatService;
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
public class PlatController {

    private final PlatService platService;

    @GetMapping(path = UrlRoute.URL_PLAT)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("plat/index");
        mav.addObject("plats", platService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_PLAT + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Plat plat = platService.getByField(field);

        mav.setViewName("plat/show");
        mav.addObject("plat", plat);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_PLAT_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new PlatDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_PLAT_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                platService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_PLAT_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("plat") PlatDTO platDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, platDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_PLAT_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("plat") PlatDTO platDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, platDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, PlatDTO dto, String uri, boolean isEdit) {
        mav.setViewName("plat/form");
        mav.addObject("plat", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, PlatDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("plat/form");
            return mav;
        }
        platService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_PLAT);
        return mav;
    }

}
