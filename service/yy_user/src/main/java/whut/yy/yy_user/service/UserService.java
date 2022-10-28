package whut.yy.yy_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whut.yy.yy_user.model.User;
import whut.yy.yy_user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User Login(User user) {
        return userRepository.getByName(user.getName());
    }
}
