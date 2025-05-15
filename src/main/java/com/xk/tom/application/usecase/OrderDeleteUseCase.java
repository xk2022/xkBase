package com.xk.tom.application.usecase;

public interface OrderDeleteUseCase {


    void deletedImportOrder(Long importId);

    void deletedExportOrder(Long exportId);
}
