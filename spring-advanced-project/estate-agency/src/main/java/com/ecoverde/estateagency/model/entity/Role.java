package com.ecoverde.estateagency.model.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority{
    private String authority;

    public Role() {
    }


    @Override
    @Column(name = "authority",unique = true)
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


}