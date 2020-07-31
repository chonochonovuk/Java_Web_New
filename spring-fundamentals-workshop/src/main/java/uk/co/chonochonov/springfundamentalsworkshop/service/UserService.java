package uk.co.chonochonov.springfundamentalsworkshop.service;

import uk.co.chonochonov.springfundamentalsworkshop.model.service.UserServiceModel;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    List<String> findAllUsernames();

    void userAddRole(String name, String role);

    UserServiceModel findById(String id);

    Long userCount();
}
