package Tests;

import models.User;


import org.junit.jupiter.api.Test;
import repositories.ContractTariffRepository;
import repositories.UserRepository;
import services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void testGetUserById(){

        UserService userService=new UserService(new UserRepository(new ContractTariffRepository()));
        User user=userService.getUserById(1);
        assertEquals(1,user.getId());

    }
}
