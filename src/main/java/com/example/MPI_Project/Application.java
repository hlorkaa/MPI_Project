package com.example.MPI_Project;

import com.example.MPI_Project.domain.Role;
import com.example.MPI_Project.domain.User;
import com.example.MPI_Project.repos.UserRepo;
import com.example.MPI_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.MPI_Project.triffid_containment_simulation.Cell;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService userService) {
        return (args) -> {
            userService.saveUser(new User("admin", "admin", Role.ADMIN));
            userService.saveUser(new User("manager", "manager", Role.MANAGER));
            userService.saveUser(new User("account", "account", Role.ACCOUNT));
            userService.saveUser(new User("consultant", "consultant", Role.CONSULTANT));
            userService.saveUser(new User("security", "security", Role.SECURITY));
            userService.saveUser(new User("workman", "workman", Role.WORKMAN));
        };
    }
}
