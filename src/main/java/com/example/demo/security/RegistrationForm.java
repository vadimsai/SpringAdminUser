package com.example.demo.security;

import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.Users;
import com.example.demo.repository.RepositorUser;
import com.example.demo.service.ServiceUserDB;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String password;


    public Users toUsers(PasswordEncoder passwordEncoder){
        Users users=new Users();
        users.setName(name);
        users.setSurname(surname);
        users.setEmail(email);
        users.setAddress(address);
        users.setPassword(passwordEncoder.encode(password)); // шифруем пароль введенный пользователем
        users.setRole(Role.USER); // по умолчанию все добавляемые юзеры- с ролью USER
        users.setStatus(Status.ACTIVE);
        return users;
    }
}
