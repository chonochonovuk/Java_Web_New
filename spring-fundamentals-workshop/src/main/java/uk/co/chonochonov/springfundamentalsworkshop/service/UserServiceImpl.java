package uk.co.chonochonov.springfundamentalsworkshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Role;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.User;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.UserServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
       userServiceModel.setRole(this.roleService.findByName(this.userRepository.count() == 0 ? "ADMIN" : "USER"));
        User user = this.modelMapper.map(userServiceModel,User.class);
        return this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
      return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<String> findAllUsernames() {
      return this.userRepository.findAll()
                .stream()
              .map(User::getUsername)
              .collect(Collectors.toList());
    }

    @Override
    public void userAddRole(String name, String role) {
        User user = this.userRepository.findByUsername(name).orElse(null);
        Role roleEntity = this.modelMapper.map(this.roleService.findByName(role),Role.class);
        if (!user.getRole().equals(roleEntity)){

            user.setRole(roleEntity);

            this.userRepository.saveAndFlush(user);

        }
    }

    @Override
    public UserServiceModel findById(String id) {

        return this.userRepository.findById(id).map(user -> this.modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }

    @Override
    public Long userCount() {

        return this.userRepository.count();
    }
}
