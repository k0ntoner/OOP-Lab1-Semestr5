package services;

import models.User;
import repositories.UserRepository;

public class UserService {


    public User getUserWithTariffById(int user_id) {
        return UserRepository.getUserWithTariffById(user_id);
    }
    public User getUserById(int user_id) {
        return UserRepository.getUserById(user_id);
    }
    public User addUser(User user) {
        return UserRepository.addUser(user);
    }
    public User getUserWithTariffByPhoneNumber(String phoneNumber) {
        return UserRepository.getUserWithTariffByPhoneNumber(phoneNumber);
    }
}
