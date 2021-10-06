package com.example.MPI_Project.repos;

import com.example.MPI_Project.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Integer>  {
    //List<Order> findByDate(String date);
}
