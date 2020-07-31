package com.exam.springexam.service;

import com.exam.springexam.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);
}
