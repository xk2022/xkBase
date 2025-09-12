package com.xk.adm.application.usecase;

import org.apache.ibatis.javassist.NotFoundException;

import java.util.UUID;

public interface DictItemDeleteUseCase {


    void delete(UUID uuid) throws NotFoundException;
}
