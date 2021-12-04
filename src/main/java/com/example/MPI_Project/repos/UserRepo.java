package com.example.MPI_Project.repos;

import com.example.MPI_Project.domain.Role;
import com.example.MPI_Project.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findByRole(Role role);
}
