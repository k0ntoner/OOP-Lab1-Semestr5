package services;

import models.User;
import repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserWithTariffById(int user_id) {
        return userRepository.getUserWithTariffById(user_id);
    }
    public User getUserById(int user_id) {
        return userRepository.getUserById(user_id);
    }
    public User addUser(User user) {
        return userRepository.addUser(user);
    }
    public User getUserWithTariffByPhoneNumber(String phoneNumber) {
        return userRepository.getUserWithTariffByPhoneNumber(phoneNumber);
    }
}
