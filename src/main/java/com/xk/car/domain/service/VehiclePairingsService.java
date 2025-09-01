package com.xk.car.domain.service;

import com.xk.car.application.model.VehiclePairingsCmd;
import com.xk.car.application.model.VehiclePairingsRequest;
import com.xk.car.domain.model.bo.VehiclePairingsBo;

import java.util.UUID;

public interface VehiclePairingsService {

    VehiclePairingsBo create(VehiclePairingsCmd cmd);

    VehiclePairingsBo update(UUID uuid, VehiclePairingsCmd cmd);

    void delete(UUID uuid);
}
