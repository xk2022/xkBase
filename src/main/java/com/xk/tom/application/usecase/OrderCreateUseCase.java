package com.xk.tom.application.usecase;



import com.xk.tom.application.model.OrderCreateDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import jakarta.validation.constraints.NotNull;

import java.text.ParseException;

public interface OrderCreateUseCase {

    OrderResponseDTO create(@NotNull OrderCreateDTO order) throws ParseException;
}
