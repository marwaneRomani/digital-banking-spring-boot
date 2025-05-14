package com.marwane.server.service;

import com.marwane.server.models.users.Agent;
import com.marwane.server.models.users.Client;
import com.marwane.server.models.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetails {
    private String username;
    private String password;
    private String role;

    public UserDetailsServiceImpl(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.role = user instanceof Agent ? "AGENT" : (user instanceof Client ? "CUSTOMER" : "ADMIN");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role)); // Prefix the role with "ROLE_"
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
