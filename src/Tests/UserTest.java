package Tests;

import interfaces.Tariff;
import models.ContractTariff;
import models.User;


import org.junit.jupiter.api.Test;
import repositories.*;
import services.DataBaseService;
import services.TariffService;
import services.UserService;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void testGetUserWithTariffById(){
        DataBaseService dataBaseService=new DataBaseService(new DataBaseRepository());
        dataBaseService.restartDataBase();
        UserService userService=new UserService(new UserRepository(new ContractTariffRepository(), new PrepaidTariffRepository()));
        TariffService tariffService=new TariffService(new TariffRepository(new ContractTariffRepository(), new PrepaidTariffRepository(), new UserRepository(new ContractTariffRepository(), new PrepaidTariffRepository())));
        ContractTariff contractTariff=new ContractTariff("ContractTariffTest", 300, 50, new Time(0,5,0), 8000 );
        Tariff tariff= tariffService.addTariff(contractTariff);
        User user=new User("UserTest", "+380999999999", 700);
        User userAfterAdd=userService.addUser(user);
        User userFromDb =userService.getUserById(userAfterAdd.getId());
        User userAfterAddingTariff= tariffService.addTariffForUser(userFromDb, tariff);
        User userFromDbAfterAddingTariff =userService.getUserWithTariffById(userAfterAddingTariff.getId());
        assertEquals(user.getUsername(),userFromDb.getUsername());
        assertEquals(user.getPhoneNumber(), userFromDb.getPhoneNumber());
        assertEquals(user.getWallet(), userFromDb.getWallet());

        assertEquals(userFromDbAfterAddingTariff.getId(), userFromDb.getId());
        ContractTariff userTariff=(ContractTariff) userFromDbAfterAddingTariff.getTariff();
        assertEquals(userTariff.getName(),contractTariff.getName());
        assertEquals(userTariff.getPrice(),contractTariff.getPrice());
        assertEquals(userTariff.getTotalMessagesCounts(), contractTariff.getTotalMessagesCounts());
        assertEquals(userTariff.getTotalMessagesCounts(),contractTariff.getTotalMessagesCounts());
        assertEquals(userTariff.getTotalInternet(),contractTariff.getTotalInternet());


    }
}
