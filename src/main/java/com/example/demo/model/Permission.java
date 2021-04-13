package com.example.demo.model;


// разрешение на- только чтение или запись

public enum Permission {
    DEVELOPERS_READ("developers: read"),
    DEVELOPERS_WRITE("developers: write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
