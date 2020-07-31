package com.examprep.springbasicexamprep.service;

import com.examprep.springbasicexamprep.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);
}
