package com.xk.tom.application.usecase;



import com.xk.tom.application.model.OrderCreateDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.bo.ImportOrderBO;

public interface ImportOrderCreateUseCase {

    OrderResponseDTO create(OrderCreateDTO order);
}
