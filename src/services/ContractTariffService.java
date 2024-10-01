package services;

import models.ContractTariff;
import repositories.ContractTariffRepository;

import java.util.List;

public class ContractTariffService {
    private final ContractTariffRepository contractTariffRepository;

    public ContractTariffService(ContractTariffRepository contractTariffRepository) {
        this.contractTariffRepository = contractTariffRepository;
    }

    public ContractTariff getContractTariffByUserId(int user_id) {
        return contractTariffRepository.getContractTariffByUserId( user_id);
    }
    public List<ContractTariff> getAllContractTariffs() {
        return contractTariffRepository.getAllContractTariffs();
    }

}
