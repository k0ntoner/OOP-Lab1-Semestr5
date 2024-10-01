package services;

import models.User;
import repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserById(int user_id) {
        return userRepository.getUserById(user_id);
    }
}
