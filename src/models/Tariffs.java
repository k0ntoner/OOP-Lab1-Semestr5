package models;

import java.util.List;

public class Tariffs {
    private List<ContractTariff> contractTariffs;
    private List<PrepaidTariff> prepaidTariffs;
    public Tariffs() {

    }

    public Tariffs(List<ContractTariff> contractTariffs, List<PrepaidTariff> prepaidTariffs) {
        this.contractTariffs = contractTariffs;
        this.prepaidTariffs = prepaidTariffs;
    }

    public List<ContractTariff> getContractTariffs() {
        return contractTariffs;
    }

    public void setContractTariffs(List<ContractTariff> contractTariffs) {
        this.contractTariffs = contractTariffs;
    }

    public List<PrepaidTariff> getPrepaidTariffs() {
        return prepaidTariffs;
    }

    public void setPrepaidTariffs(List<PrepaidTariff> prepaidTariffs) {
        this.prepaidTariffs = prepaidTariffs;
    }
}
