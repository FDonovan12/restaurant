package com.example.restaurant.controller.admin;

import com.example.restaurant.entity.Reservation;
import com.example.restaurant.DTO.ReservationDTO;
import com.example.restaurant.service.ReservationService;
import com.example.restaurant.mapping.UrlRoute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class AdminReservationController {

    private final ReservationService reservationService;

    @GetMapping(path = UrlRoute.URL_ADMIN_RESERVATION)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/reservation/index");
        mav.addObject("reservations", reservationService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_RESERVATION + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Reservation reservation = reservationService.getByField(field);

        mav.setViewName("admin/reservation/show");
        mav.addObject("reservation", reservation);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_RESERVATION_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new ReservationDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_RESERVATION_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                reservationService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_RESERVATION_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("reservation") ReservationDTO reservationDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, reservationDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_ADMIN_RESERVATION_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("reservation") ReservationDTO reservationDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, reservationDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, ReservationDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/reservation/form");
        mav.addObject("reservation", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, ReservationDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/reservation/form");
            return mav;
        }
        reservationService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_RESERVATION);
        return mav;
    }

}
