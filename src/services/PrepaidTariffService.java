package services;

import models.PrepaidTariff;
import repositories.PrepaidTariffRepository;

import java.util.List;

public class PrepaidTariffService {
    private final PrepaidTariffRepository prepaidTariffRepository;

    public PrepaidTariffService(PrepaidTariffRepository prepaidTariffRepository) {
        this.prepaidTariffRepository = prepaidTariffRepository;
    }

    public PrepaidTariff getPrepaidTariffByUserId(int user_id) {
        return prepaidTariffRepository.getPrepaidTariffByUserId( user_id);
    }
    public List<PrepaidTariff> getAllPrepaidTariffs() {
        return prepaidTariffRepository.getAllPrepaidTariffs();
    }
}
