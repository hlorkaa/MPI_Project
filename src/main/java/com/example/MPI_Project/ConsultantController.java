package com.example.MPI_Project;

import com.example.MPI_Project.domain.OrderCard;
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

    public void putVariables(Map<String, Object> model, Integer id, String name, String customer, String date, String deadline, String quality, Integer quantity, String notes) {
        Iterable<OrderCard> orders = orderRepo.findAll();
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

    public OrderCard findOrder(Integer id) { return orderRepo.findById(id).orElse(new OrderCard()); }

    @GetMapping
    public String start(Map<String, Object> model) {

        putVariables(model, 0,  "",  "",  "",  "",  "", 0, "");

        return "consultant";
    }

    @PostMapping("/create")
    public String createNewOrder (
                                  @RequestParam String newOrder_name,
                                  @RequestParam String newOrder_customer,
                                  @RequestParam String newOrder_date,
                                  @RequestParam String newOrder_deadline,
                                  @RequestParam String newOrder_quality,
                                  @RequestParam Integer newOrder_quantity,
                                  @RequestParam(defaultValue = "") String newOrder_notes,
                                  Map<String, Object> model
                                 ) {
        OrderCard newOrderCard = new OrderCard(newOrder_name, newOrder_customer, newOrder_date, newOrder_deadline, newOrder_quality, newOrder_quantity, newOrder_notes);

        if (!newOrder_name.equals("") && !newOrder_customer.equals("") && !newOrder_date.equals("") && !newOrder_deadline.equals("") && !newOrder_quality.equals("") && newOrder_quantity != 0) {
            orderRepo.save(newOrderCard);
        }

        putVariables(model, 0,  "",  "",  "",  "",  "", 0, "");

        return "consultant";
    }

    @PostMapping("/delete")
    public String deleteOrder (@RequestParam Integer deleteOrder_id, Map<String, Object> model) {
        orderRepo.deleteById(deleteOrder_id);

        putVariables(model, 0,  "",  "",  "",  "",  "", 0, "");

        return "consultant";
    }

    @PostMapping("/choose")
    public String chooseOrder (
                               @RequestParam Integer chooseOrder_id,
                               Map<String, Object> model
                              ) {
        OrderCard orderCard = findOrder(chooseOrder_id);
        String order_name = orderCard.getOrderName();
        String order_customer = orderCard.getCustomer();
        String order_date = orderCard.getDate();
        String order_deadline = orderCard.getOrderDeadline();
        String order_quality = orderCard.getQuality();
        Integer order_quantity = orderCard.getQuantity();
        String order_notes = orderCard.getNotes();

        putVariables(model, chooseOrder_id,  order_name,  order_customer,  order_date,  order_deadline,  order_quality, order_quantity, order_notes);

        return "consultant";
    }

    @PostMapping("/edit")
    public String editOrder (
                             @RequestParam(defaultValue = "0") Integer order_id,
                             @RequestParam String order_name,
                             @RequestParam String order_customer,
                             @RequestParam String order_date,
                             @RequestParam String order_deadline,
                             @RequestParam String order_quality,
                             @RequestParam(defaultValue = "") Integer order_quantity,
                             @RequestParam(defaultValue = "") String order_notes,
                             Map<String, Object> model
                            ) {
        if (order_id != 0 && !order_name.equals("") && !order_customer.equals("") && !order_date.equals("") && !order_deadline.equals("") && !order_quality.equals("") && !order_quantity.equals("0")) {
            OrderCard orderCard = findOrder(order_id);

            orderCard.setOrderName(order_name);
            orderCard.setCustomer(order_customer);
            orderCard.setDate(order_date);
            orderCard.setOrderDeadline(order_deadline);
            orderCard.setQuality(order_quality);
            orderCard.setQuantity(order_quantity);
            orderCard.setNotes(order_notes);

            orderRepo.save(orderCard);
        }

        putVariables(model, 0,  "",  "",  "",  "",  "", 0, "");

        return "consultant";
    }

    @PostMapping("/cancel")
    public String cancelOrderEdition (Map<String, Object> model) {
        putVariables(model, 0,  "",  "",  "",  "",  "", 0, "");

        return "consultant";
    }

}
