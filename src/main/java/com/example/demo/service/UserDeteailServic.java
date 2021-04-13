package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.RepositorUser;
import com.example.demo.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDeteailServic")
public class UserDeteailServic implements UserDetailsService {

    private final RepositorUser repositorUser;

    @Autowired
    public UserDeteailServic(RepositorUser repositorUser) {
        this.repositorUser = repositorUser;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users=repositorUser.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        return SecurityUser.fromuser(users);
    }



}
