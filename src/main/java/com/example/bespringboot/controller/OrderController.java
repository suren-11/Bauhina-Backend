package com.example.bespringboot.controller;

import com.example.bespringboot.entity.CartItem;
import com.example.bespringboot.entity.Order;
import com.example.bespringboot.entity.OrderReport;
import com.example.bespringboot.entity.Product;
import com.example.bespringboot.service.CartItemService;
import com.example.bespringboot.service.OrderRepoService;
import com.example.bespringboot.service.OrderService;
import com.example.bespringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class OrderController {
    private final OrderService orderService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final OrderRepoService orderRepoService;

    /*@PostMapping("/order/save")
    public void save(@ModelAttribute CartItem[] cartItems, @ModelAttribute Orders order){
        orderSave(order);
        //return cartItemService.save(cartItems);
    }*/

   /* public Order createOrder(@RequestBody Order order){
        return orderService.save(order);
    }
    public CartItem cartItems(@RequestBody CartItem[] cartItems){
        for (CartItem c: cartItems
             ) {
            orderService.save(c);
        }
        return null;
    }*/

    /*public Orders orderSave(Orders orders){
        return orderService.save(orders);
    }*/

    @PostMapping(value = "/order")
    public Order createOrder(@RequestBody Order order) {
        List<CartItem> cartItems = order.getCartItems();
        order.setCartItems(null);
        orderService.save(order);
        saveReport(order);
        Order createdOrder = orderService.findTopByOrderByIdDesc();
        List<CartItem> newCart =  cartItemSave(cartItems,createdOrder);
        createdOrder.setCartItems(newCart);
        return createdOrder;
    }

    /** saving the cartitems. if any order is placed that selected items are
     * save from cartItem table form this method. */
    public List<CartItem> cartItemSave(List<CartItem> cartItems, Order order){
        if (cartItems !=null){
            for (CartItem cartItem: cartItems){
                cartItem.setOrder(order);
                updateProductQty(cartItem.getProductId(),cartItem.getQty());
                cartItemService.save(cartItem);
            }
        }
        return cartItems;
    }

    /**updating a product quantity if any order is placed 
     * this method update the qty on hand  */
    public void updateProductQty(String code, int qty){
        Optional<Product> product = productService.getProductForUpdate(code);
        if (product.isPresent()) {
            int existingQty = product.get().getQty();
            int tempQty = existingQty - qty;
            product.get().setQty(tempQty);
        }
    }

    /** getting the all of the orders from database */
    @GetMapping("/getOrders")
    public List<Order> ordersList(){ return orderService.findAll();}

    @GetMapping("/getDate/{date}")
    public List<Order> getDetailsByDate(@PathVariable String date){
        return orderService.findByDate(date);
    }

    @GetMapping("/getOrders/cartItems")
    public List<CartItem> getCartItems(){
        return cartItemService.getList();
    }

    @GetMapping("/getOrder/customer/{id}")
    public List<Order> getCustomerOrders(@PathVariable String id){ return orderService.findByCustomerId(id);}

    /**if any order is placed this method calculate the report details
     * based on the order details */
    public OrderReport saveReport(Order order){
        Optional<OrderReport> existingOrder = orderRepoService.findByDate(order.getDate());
        List<CartItem> cartItem = order.getCartItems();
        Double totalCost = calculateTotalCost(cartItem);
        /**if report have order date it will update the records */
        if (existingOrder.isPresent()){
            OrderReport existingOrderReport = existingOrder.get();
            int existingOrders = existingOrderReport.getTotalOrders();
            int updatedOrderNos = existingOrders+1;
            Double existingCost = existingOrderReport.getTotalCost();
            Double updatedCost = existingCost + totalCost;
            Double existingTotalPayment = existingOrderReport.getTotalPayment();
            existingOrderReport.setTotalOrders(updatedOrderNos);
            existingOrderReport.setTotalPayment(existingTotalPayment+order.getTotal());
            existingOrderReport.setTotalCost(updatedCost);
            return orderRepoService.saveOrderReport(existingOrderReport);
        }
        /** if report doesn't have a order date then it will save the date and details */
        OrderReport orderReport = new OrderReport();
        orderReport.setDate(order.getDate());
        orderReport.setTotalOrders(1);
        orderReport.setTotalCost(totalCost);
        orderReport.setTotalPayment(order.getTotal());
        return orderRepoService.saveOrderReport(orderReport);
    }

    /** calculate the total cost of the placed order cartitems for the income report */
    public Double calculateTotalCost(List<CartItem> cartItems){
        double totalCost = 0.0;

        for (CartItem c: cartItems
             ) {
            Optional<Product> optionalProduct = productService.getByCode(c.getProductId());
            if (optionalProduct.isPresent()){
                Product product = optionalProduct.get();
                totalCost += product.getCost();
            }
        }
        return totalCost;
    }

}
