package com.example.demo.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


// связываем роли (USER,ADMIN) с разрешениями(permission- DEVELOPERS_READ,DEVELOPERS_WRITE)
public enum Role {

    USER(Set.of(Permission.DEVELOPERS_READ)),
    ADMIN(Set.of(Permission.DEVELOPERS_READ,Permission.DEVELOPERS_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }


    // на основании разрешений(permission) получаем GrantedAuthority о которых знает SpringSecurity(UserDetails->SimpleGrantedAuthority(реализует определение ролей) )
    public Set<SimpleGrantedAuthority> getAuthority()
    {
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
    }
}
