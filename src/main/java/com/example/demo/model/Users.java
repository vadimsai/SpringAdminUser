package com.example.demo.model;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


@Data
@Entity
@Table(name="userss")
public class Users implements PasswordEncoder{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    public Users() {
    }

    public Users(String name, String surname, String email, String address, String password, Role role, Status status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.password = Encode(password);
        this.role = role;
        this.status = status;
    }

    public Users(long id, String name, String surname, String email, String address, String password, Role role, Status status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.password =Encode(password);
        this.role = role;
        this.status = status;
    }

    public String Encode(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }


    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
