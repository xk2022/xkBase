package com.xk.tom.application.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record OrderRequestDTO(

        String status,
        String customerName
) {


}
