package uk.co.chonochonov.springfundamentalsworkshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Role;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.RoleServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.repository.RoleRepository;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        if (this.roleRepository.count() == 0) {
            Role admin = new Role();
            Role user = new Role();
            admin.setName("ADMIN");
            user.setName("USER");
            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }

    @Override
    public RoleServiceModel findByName(String name) {

        return this.roleRepository.findByName(name).map(role -> this.modelMapper.map(
                role,RoleServiceModel.class
        )).orElse(null);
    }
}
