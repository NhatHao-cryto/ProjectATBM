package com.example.web.controller.OrderItemController;

import com.example.web.dao.OrderItemDao;
import com.example.web.dao.model.OrderItem;
import com.example.web.service.OrderItemService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListOrderItemsByOrder", value = "/order/order-items")
public class GetListByOrder extends HttpServlet {
    private OrderItemService orderItemService;
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            String orderIdParam = req.getParameter("orderId");
            int orderId = Integer.parseInt(orderIdParam);
            orderItemService = new OrderItemService();

            try {
                List<OrderItem> orderItems = orderItemService.getOrderItems(orderId);

                if (orderItems == null || orderItems.isEmpty()) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\": \"No items found for this order\"}");
                    return;
                }

                System.out.println(orderItems + "lần");

                Gson gson = new Gson();
                String json = gson.toJson(orderItems);
                resp.getWriter().write(json);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }