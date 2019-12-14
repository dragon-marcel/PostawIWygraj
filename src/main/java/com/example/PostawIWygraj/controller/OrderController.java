package com.example.PostawIWygraj.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.PostawIWygraj.model.Order;
import com.example.PostawIWygraj.model.UserPrincipal;
import com.example.PostawIWygraj.service.OrderService;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders")
    public String getOrders(Model model) {
	List<Order> orders = orderService.findAll();
	model.addAttribute("orders", orders);
	return "orders";
    }

    @RequestMapping(value = "/my-orders")
    public String getOrder(Model model) {
	UserPrincipal usersPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
	Long idUser = usersPrincipal.getUser().getId();
	List<Order> orders = orderService.findAllByIdUser(idUser);
	model.addAttribute("orders", orders);

	return "my_orders";
    }

    @RequestMapping(value = "/my_order/{id}")
    public String getOrderByID(@PathVariable("id") Long id) {
	return "order";
    }

}
