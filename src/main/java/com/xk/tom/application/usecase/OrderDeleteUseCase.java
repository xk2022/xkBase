package com.xk.tom.application.usecase;

import com.xk.tom.application.model.OrderResponseDTO;

public interface OrderDeleteUseCase {


    void deletedImportOrder(Long importId);

    void deletedExportOrder(Long exportId);

}
