package com.example.demo.service;


import com.example.demo.model.Users;
import com.example.demo.repository.RepositorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUserDB {

    private final RepositorUser repositorUser;

    @Autowired
    public ServiceUserDB(RepositorUser repositorUser) {
        this.repositorUser = repositorUser;
    }


    public List<Users> findById(long id) {
        return  repositorUser.findById(id);
    }

    public List<Users> findAll(){
        return repositorUser.findAll();
    }

    public Users save(Users users){
        return repositorUser.save(users);
    }

    public void delete(long id){
        repositorUser.deleteById(id);
    }

    public List<Users> findBySur(String surname){return repositorUser.findBySurname( surname);}

    public Optional<Users> findFirstByEmail(String name){return repositorUser.findFirstByEmail(name);}

    public List<Users> findAllByEmail(String email){return repositorUser.findAllByEmail(email);}

}
