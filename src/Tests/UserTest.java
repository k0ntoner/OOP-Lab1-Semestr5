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
        DataBaseService dataBaseService=new DataBaseService();
        dataBaseService.restartDataBase();
        UserService userService=new UserService();
        TariffService tariffService=new TariffService();
        ContractTariff contractTariff=new ContractTariff("ContractTariffTest", 8000, 50, new Time(0,5,0), 300);
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
