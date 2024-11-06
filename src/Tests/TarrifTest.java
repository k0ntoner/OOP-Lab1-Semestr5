package Tests;

import interfaces.Tariff;
import models.ContractTariff;
import models.PrepaidTariff;
import models.User;
import org.junit.jupiter.api.Test;
import repositories.*;
import services.*;

import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarrifTest {

    @Test
    public void testGetAllUsersWithTariff(){
        TariffService tariffService=new TariffService();
        DataBaseService dataBaseService=new DataBaseService();
        dataBaseService.restartDataBase();
        UserService userService=new UserService();
        PrepaidTariff prepaidTariff=new PrepaidTariff("PrepaidTariffTest", 200, 0.5, 1, 5 );
        ContractTariff contractTariff=new ContractTariff("ContractTariffTest", 8000, 50, new Time(0,5,0), 300);
        Tariff tariff1= tariffService.addTariff(contractTariff);
        Tariff tariff2= tariffService.addTariff(prepaidTariff);

        User user1=new User("UserTest", "+380999999999", 700);
        User user2=new User("UserTest2", "+380999999998", 700);
        User user3=new User("UserTest3", "+380999999997", 700);
        User user1AfterAdd=userService.addUser(user1);
        User user1FromDb =userService.getUserById(user1AfterAdd.getId());
        User user2AfterAdd=userService.addUser(user2);
        User user2FromDb=userService.getUserById(user2AfterAdd.getId());
        User user3AfterAdd=userService.addUser(user3);
        User user3FromDb=userService.getUserById(user3AfterAdd.getId());
        User user3AfterAddingTariff= tariffService.addTariffForUser(user3FromDb, tariff1);
        User user1AfterAddingTariff= tariffService.addTariffForUser(user1FromDb, tariff1);
        User user2AfterAddingTariff = tariffService.addTariffForUser(user2FromDb, tariff2);
        List<User> users= tariffService.getAllUsersWithTariff();
        assertEquals(3,users.size());
    }
    @Test
    public void testGetAllTariffs(){
        TariffService tariffService=new TariffService();
        DataBaseService dataBaseService=new DataBaseService();
        dataBaseService.restartDataBase();
        PrepaidTariff prepaidTariff=new PrepaidTariff("PrepaidTariffTest", 200, 0.5, 1, 5 );
        ContractTariff contractTariff=new ContractTariff("ContractTariffTest", 8000, 50, new Time(0,5,0), 300);
        ContractTariff contractTariff2=new ContractTariff("ContractTariffTest2", 8000, 50, new Time(0,5,0), 150);
        Tariff tariff1= tariffService.addTariff(contractTariff);
        Tariff tariff2= tariffService.addTariff(prepaidTariff);
        Tariff tariff3= tariffService.addTariff(contractTariff2);
        List<Tariff> tariffs= tariffService.getAllTariffs();
        assertEquals(3,tariffs.size());
    }
    @Test
    public void testGetTariffsSortingByPrice(){
        TariffService tariffService=new TariffService();
        DataBaseService dataBaseService=new DataBaseService();
        dataBaseService.restartDataBase();
        PrepaidTariff prepaidTariff=new PrepaidTariff("PrepaidTariffTest", 200, 0.5, 1, 5 );
        ContractTariff contractTariff=new ContractTariff("ContractTariffTest", 8000, 50, new Time(0,5,0), 300);
        ContractTariff contractTariff2=new ContractTariff("ContractTariffTest2", 8000, 50, new Time(0,5,0), 150);
        Tariff tariff1= tariffService.addTariff(contractTariff);
        Tariff tariff2= tariffService.addTariff(prepaidTariff);
        Tariff tariff3= tariffService.addTariff(contractTariff2);
        List<Tariff> tariffs= tariffService.getAllTariffs();
        assertEquals(3,tariffs.size());
        List<Tariff> tariffsSortingByPrice= tariffService.getAllSortingTariffsByPrice(tariffs);
        assertEquals(3,tariffsSortingByPrice.size());
        assertEquals(tariffsSortingByPrice.get(0).getPrice()<tariffsSortingByPrice.get(1).getPrice(), true);
        assertEquals(tariffsSortingByPrice.get(1).getPrice()<tariffsSortingByPrice.get(2).getPrice(), true);
    }

    @Test
    public void testFindTariffByParameters(){
        TariffService tariffService=new TariffService();
        DataBaseService dataBaseService=new DataBaseService();
        dataBaseService.restartDataBase();
        PrepaidTariff prepaidTariff=new PrepaidTariff("PrepaidTariffTest", 200, 0.5, 1, 5 );
        ContractTariff contractTariff=new ContractTariff("ContractTariffTest", 8000, 50, new Time(0,5,0), 300);
        ContractTariff contractTariff2=new ContractTariff("ContractTariffTest2", 8000, 50, new Time(0,5,0), 150);
        Tariff tariff1= tariffService.addTariff(contractTariff);
        Tariff tariff2= tariffService.addTariff(prepaidTariff);
        Tariff tariff3= tariffService.addTariff(contractTariff2);
        Tariff tariff1ByParameters = tariffService.findTariffByParameters(50, new Time(0,5,0), 8000, 300);
        Tariff tariff2ByParameters= tariffService.findTariffByParameters(0.5, 1, 200, 5);
        Tariff tariff3ByParameters = tariffService.findTariffByParameters(50, new Time(0,5,0), 8000, 150);
        Tariff nullTariff=tariffService.findTariffByParameters(0, new Time(0,5,0), 8000, 0);
        assertEquals(tariff1ByParameters.getId(),tariff1.getId());
        assertEquals(tariff2ByParameters.getId(),tariff2.getId());
        assertEquals(tariff3ByParameters.getId(),tariff3.getId());
        assertEquals(nullTariff,null);


    }
}
