package com.example.MPI_Project.repos;

import com.example.MPI_Project.domain.OrderCard;
import com.example.MPI_Project.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<OrderCard, Integer>  {
//    List <Order> findByDate(String date);
    OrderCard findByOrdername(String ordername);
    void deleteByOrdername(String ordername);
}
