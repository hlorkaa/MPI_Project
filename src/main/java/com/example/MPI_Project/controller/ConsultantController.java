package com.example.MPI_Project.controller;

import com.example.MPI_Project.domain.Finances;
import com.example.MPI_Project.domain.OrderCard;
import com.example.MPI_Project.repos.FinancesRepo;
import com.example.MPI_Project.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private FinancesRepo financesRepo;

    public void pushToFinances(String date, String oldQuality, Integer oldQuantity, String newQuality, Integer newQuantity) {
        Double amount;
        String type;
        Integer oldQualityCoefficient = 0;
        switch (oldQuality){
            case "Техническое":
                oldQualityCoefficient = 1;
            case "Обычное":
                oldQualityCoefficient = 2;
            case "Высококачественное":
                oldQualityCoefficient = 3;
        }

        Integer newQualityCoefficient = 0;
        switch (newQuality){
            case "Техническое":
                newQualityCoefficient = 1;
            case "Обычное":
                newQualityCoefficient = 2;
            case "Высококачественное":
                newQualityCoefficient = 3;
        }

        amount = Double.valueOf(newQuantity * newQualityCoefficient - oldQuantity * oldQualityCoefficient);

        if (amount >= 0) {
            type = "Income";
        }
        else {
            type = "Outcome";
        }
        if (amount != 0) {
            Finances finances = new Finances(date, Math.abs(amount), type);

            financesRepo.save(finances);
        }

    }

    public void putVariables(Map<String, Object> model, Integer id, String name, String customer, String date, String deadline, String quality, Integer quantity, String notes) {
        Integer flag;
        if (orderRepo == null)
            flag = 0;
        //Iterable<OrderCard> orders = orderRepo.findAll();
        Iterable<OrderCard> orders = null;
        model.put("orders", orders);
        model.put("order_id", id);
        model.put("order_name", name);
        model.put("order_customer", customer);
        model.put("order_date", date);
        model.put("order_deadline", deadline);
        model.put("order_quality", quality);
        model.put("order_quantity", quantity);
        model.put("order_notes", notes);
    }

    public OrderCard findOrder(Integer id) {
        return orderRepo.findById(id).orElse(new OrderCard());
    }

    @GetMapping
    public String start(Map<String, Object> model) {

        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }

    @PostMapping("/create")
    public String createNewOrder(
            @RequestParam String newOrder_name,
            @RequestParam String newOrder_customer,
            @RequestParam String newOrder_date,
            @RequestParam String newOrder_deadline,
            @RequestParam String newOrder_quality,
            @RequestParam(defaultValue = "0") Integer newOrder_quantity,
            @RequestParam(defaultValue = "") String newOrder_notes,
            Map<String, Object> model
    ) {
        OrderCard newOrder = new OrderCard(newOrder_name, newOrder_customer, newOrder_date, newOrder_deadline, newOrder_quality, newOrder_quantity, newOrder_notes);


        if (!newOrder_name.equals("") && !newOrder_customer.equals("") && !newOrder_date.equals("") && !newOrder_deadline.equals("") && !newOrder_quality.equals("") && !newOrder_quantity.equals("0")) {
            orderRepo.save(newOrder);
        }
        pushToFinances(newOrder_date, "None", 0, newOrder_quality, newOrder_quantity);
        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam Integer deleteOrder_id, Map<String, Object> model) {
        OrderCard deletedOrder = findOrder(deleteOrder_id);
        pushToFinances(deletedOrder.getDate(), deletedOrder.getQuality(), deletedOrder.getQuantity(), "None", 0);
        orderRepo.deleteById(deleteOrder_id);

        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }

    @PostMapping("/choose")
    public String chooseOrder(
            @RequestParam Integer chooseOrder_id,
            Map<String, Object> model
    ) {
        OrderCard order = findOrder(chooseOrder_id);
        String order_name = order.getOrdername();
        String order_customer = order.getCustomer();
        String order_date = order.getDate();
        String order_deadline = order.getOrderdeadline();
        String order_quality = order.getQuality();
        Integer order_quantity = order.getQuantity();
        String order_notes = order.getNotes();

        putVariables(model, chooseOrder_id, order_name, order_customer, order_date, order_deadline, order_quality, order_quantity, order_notes);

        return "consultant_temp";
    }

    @PostMapping("/edit")
    public String editOrder(
            @RequestParam(defaultValue = "0") Integer order_id,
            @RequestParam String order_name,
            @RequestParam String order_customer,
            @RequestParam String order_date,
            @RequestParam String order_deadline,
            @RequestParam String order_quality,
            @RequestParam(defaultValue = "0") Integer order_quantity,
            @RequestParam(defaultValue = "") String order_notes,
            Map<String, Object> model
    ) {
        if (order_id != 0 && !order_name.equals("") && !order_customer.equals("") && !order_date.equals("") && !order_deadline.equals("") && !order_quality.equals("") && !order_quantity.equals("0")) {
            OrderCard order = findOrder(order_id);

            pushToFinances(order_date, order.getQuality(), order.getQuantity(), order_quality, order_quantity);

            order.setOrdername(order_name);
            order.setCustomer(order_customer);
            order.setDate(order_date);
            order.setOrderdeadline(order_deadline);
            order.setQuality(order_quality);
            order.setQuantity(order_quantity);
            order.setNotes(order_notes);

            orderRepo.save(order);
        }

        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }

    @PostMapping("/cancel")
    public String cancelOrderEdition(Map<String, Object> model) {
        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }

    @PostMapping("/exit")
    public String goToMain (Map<String, Object> model) {
        return "redirect:/main";
    }
}