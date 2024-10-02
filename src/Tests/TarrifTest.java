package Tests;

import models.ContractTariff;
import models.PrepaidTariff;
import org.junit.jupiter.api.Test;
import repositories.ContractTariffRepository;
import repositories.PrepaidTariffRepository;
import services.ContractTariffService;
import services.PrepaidTariffService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarrifTest {
    @Test
    public void testGetContractTariffByUserId(){

        ContractTariffService contractTariffService=new ContractTariffService(new ContractTariffRepository());
        ContractTariff contractTariff=contractTariffService.getContractTariffByUserId(1);
        assertEquals(1,contractTariff.getId());

    }
    @Test
    public void testGetPrepaidTariffByUserId(){
        PrepaidTariffService prepaidTariffService =new PrepaidTariffService(new PrepaidTariffRepository());
        PrepaidTariff prepaidTariff = prepaidTariffService.getPrepaidTariffByUserId(1);
        assertEquals(1,prepaidTariff.getId());



    }
    @Test
    public void testGetAllUsersWithTariff(){



    }
    @Test
    public void testFindTariffByParameters(){



    }
}
