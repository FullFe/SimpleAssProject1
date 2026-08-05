package org.example.service;

import org.example.dto.userRequestsNResponses.UserRequest;
import org.example.model.Rights;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequest request){
        User user = new User(request.getLogin(), request.getPass());
        if (request.getRights() != null){
            user.setRole(Rights.valueOf(request.getRights()));
        }

        userRepository.save(user);
    }

    public void deleteUser(UserRequest request){
        Optional<User> optionalUser = userRepository.findByLogin(request.getLogin());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            //TODO Додумать как можно быть авторизованным или проверять, что за пользователь производит действие.
            userRepository.delete(optionalUser.get());
        }
    }

//    public User getUser(UserRequest request){
//        return userRepository.findByLogin(request.getLogin()).orElse(null);
//    }

    public void updateUser(UserRequest request){
        Optional<User> optionalUser = userRepository.findByLogin(request.getLogin());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if (request.getRights() != null){
                user.setRole(Rights.valueOf(request.getRights()));
            }
            userRepository.save(user);
        }
    }
}
