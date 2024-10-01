package services;

import models.PrepaidTariff;

import java.util.List;

public class PrepaidTariffService {
    private final PrepaidTariffService prepaidTariffRepository;

    public PrepaidTariffService(PrepaidTariffService prepaidTariffRepository) {
        this.prepaidTariffRepository = prepaidTariffRepository;
    }

    public PrepaidTariff getPrepaidTariffByUserId(int user_id) {
        return prepaidTariffRepository.getPrepaidTariffByUserId( user_id);
    }
    public List<PrepaidTariff> getAllPrepaidTariffs() {
        return prepaidTariffRepository.getAllPrepaidTariffs();
    }
}
