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
        TariffService tariffService=new TariffService(new TariffRepository(new ContractTariffRepository(), new PrepaidTariffRepository(), new UserRepository(new ContractTariffRepository(),new PrepaidTariffRepository())));
        DataBaseService dataBaseService=new DataBaseService(new DataBaseRepository());
        dataBaseService.restartDataBase();
        UserService userService=new UserService(new UserRepository(new ContractTariffRepository(), new PrepaidTariffRepository()));
        PrepaidTariff prepaidTariff=new PrepaidTariff("PrepaidTariffTest", 200, 0.5, 1, 5 );
        ContractTariff contractTariff=new ContractTariff("ContractTariffTest", 300, 50, new Time(0,5,0), 8000 );
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
    public void testFindTariffByParameters(){



    }
}
