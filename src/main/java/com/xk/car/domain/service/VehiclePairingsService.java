package com.xk.car.domain.service;

import com.xk.car.application.model.VehiclePairingsCmd;
import com.xk.car.domain.model.bo.VehiclePairingsBo;

public interface VehiclePairingsService {

    VehiclePairingsBo create(VehiclePairingsCmd cmd);
}
