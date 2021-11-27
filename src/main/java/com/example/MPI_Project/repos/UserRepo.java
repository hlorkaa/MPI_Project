package com.example.MPI_Project.repos;

import com.example.MPI_Project.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
