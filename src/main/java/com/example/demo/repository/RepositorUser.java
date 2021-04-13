package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositorUser extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    List<Users> findBySurname(String surname);
    Optional<Users> findFirstByEmail(String name);
    List<Users> findAllByEmail(String email);
    List<Users> findById(long id);
}
