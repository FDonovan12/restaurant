package com.example.restaurant.DTO;

import com.example.restaurant.repository.PlatRepository;
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
public class PlatDTO {

    @NotBlank(message = "This should be a valid name")
    private String name;

}