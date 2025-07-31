package com.xk.tom.application.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImportOrderDTO(
        @NotBlank
        String orderid
) {


}
