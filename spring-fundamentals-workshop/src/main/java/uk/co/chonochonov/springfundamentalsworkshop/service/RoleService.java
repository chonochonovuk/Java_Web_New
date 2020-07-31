package uk.co.chonochonov.springfundamentalsworkshop.service;

import uk.co.chonochonov.springfundamentalsworkshop.model.service.RoleServiceModel;

public interface RoleService {
    RoleServiceModel findByName(String name);
}
