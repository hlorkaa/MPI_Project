package com.example.MPI_Project.service;

import com.example.MPI_Project.domain.OrderCard;
import com.example.MPI_Project.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    public boolean saveOrder(OrderCard order) {
        OrderCard orderFromDB = orderRepo.findByOrdername(order.getOrdername());
        if (orderFromDB != null) {
            return false;
        }
        orderRepo.save(order);
        return true;
    }

    public boolean deleteOrder(String name) {
        OrderCard orderFromDB = orderRepo.findByOrdername(name);
        if (orderFromDB != null) {
            orderRepo.deleteByOrdername(name);
            return true;
        }
        return false;
    }
}
