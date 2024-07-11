package com.example.restaurant.DTO;

import com.example.restaurant.repository.ReservationRepository;
import com.example.restaurant.validator.annotation.UniqueName;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    @NotBlank(message = "This should be a valid name")
    private String name;

    private List<Long> clientId;
    private List<Long> PlatId;

}