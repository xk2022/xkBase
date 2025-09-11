package com.xk.adm.application.usecase;

import com.xk.adm.application.dto.DictCategoryResponse;
import com.xk.adm.application.dto.DictItemRequest;
import com.xk.adm.application.dto.DictItemResponse;
import jakarta.validation.Valid;
import org.apache.ibatis.javassist.NotFoundException;

public interface DictItemCreateUseCase {

    DictItemResponse create(@Valid DictItemRequest request) throws NotFoundException;
}
