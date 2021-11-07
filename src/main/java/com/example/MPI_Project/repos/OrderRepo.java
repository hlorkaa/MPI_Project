package com.example.MPI_Project.repos;

import com.example.MPI_Project.domain.OrderCard;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<OrderCard, Integer>  {
//    List <Order> findByDate(String date);
}
