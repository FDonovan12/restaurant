package com.example.restaurant.controller.admin;

import com.example.restaurant.entity.Client;
import com.example.restaurant.DTO.ClientDTO;
import com.example.restaurant.service.ClientService;
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
public class AdminClientController {

    private final ClientService clientService;

    @GetMapping(path = UrlRoute.URL_ADMIN_CLIENT)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/client/index");
        mav.addObject("clients", clientService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_CLIENT + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Client client = clientService.getByField(field);

        mav.setViewName("admin/client/show");
        mav.addObject("client", client);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_CLIENT_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new ClientDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_CLIENT_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                clientService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_CLIENT_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("client") ClientDTO clientDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, clientDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_ADMIN_CLIENT_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("client") ClientDTO clientDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, clientDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, ClientDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/client/form");
        mav.addObject("client", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, ClientDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/client/form");
            return mav;
        }
        clientService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_CLIENT);
        return mav;
    }

}
