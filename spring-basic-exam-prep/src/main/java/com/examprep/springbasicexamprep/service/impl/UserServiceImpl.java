package com.examprep.springbasicexamprep.service.impl;

import com.examprep.springbasicexamprep.model.entity.User;
import com.examprep.springbasicexamprep.model.service.UserServiceModel;
import com.examprep.springbasicexamprep.repository.UserRepository;
import com.examprep.springbasicexamprep.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        return this.modelMapper.map( this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository.findByUsername(username).
                map(user -> this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }
}
