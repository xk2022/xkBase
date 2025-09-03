package com.xk.adm.application.usecase;

import com.xk.adm.application.dto.DictCategoryRequest;
import com.xk.adm.application.dto.DictCategoryResponse;
import jakarta.validation.Valid;

public interface DictCategoryCreateUseCase {
    DictCategoryResponse create(@Valid DictCategoryRequest request);
}
