package com.example.demo.security;

import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.Users;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

// надо преобразовать нашего юзера в этого юзера

@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;


    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password =password;
        this.authorities = authorities;
        this.isActive = isActive;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }


    public static UserDetails fromuser(Users users){
        return new User(
                users.getEmail(),users.getPassword(),
                users.getStatus().equals(Status.ACTIVE),
                users.getStatus().equals(Status.ACTIVE),
                users.getStatus().equals(Status.ACTIVE),
                users.getStatus().equals(Status.ACTIVE),
                users.getRole().getAuthority());
    }





}
