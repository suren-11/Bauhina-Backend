package com.example.bespringboot.controller;

import com.example.bespringboot.entity.Customer;
import com.example.bespringboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
/**connect a angular to spring boot */
@CrossOrigin("http://localhost:4200")
public class CustomerController {

    /** intializing a JPA methods to the controller class */
    private final CustomerService customerService;


    /** saving a customer to the database in here pass the value to the customer service */
    @PostMapping("/save")
    public Customer postCustomer(@RequestBody Customer customer ){
        return customerService.postCustomer(customer);
    }

    @GetMapping("/getList")
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping(value = "/login/{email}")
    public ResponseEntity<Customer> login(@PathVariable String email) {
        Optional<Customer> userFromDb = customerService.findByEmail(email);
        if (userFromDb.isPresent()) {
            return ResponseEntity.ok(userFromDb.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        Optional<Customer> selectedCustomer = customerService.findById(id);
        if (selectedCustomer.isPresent()){
            Customer updateCustomer = selectedCustomer.get();
            updateCustomer.setFirstName(customer.getFirstName());
            updateCustomer.setLastName(customer.getLastName());
            updateCustomer.setAddress(customer.getAddress());
            updateCustomer.setPhone1(customer.getPhone1());
            updateCustomer.setPhone2(customer.getPhone2());
            updateCustomer.setEmail(customer.getEmail());
            updateCustomer.setPassword(customer.getPassword());
            customerService.postCustomer(updateCustomer);
            return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        Optional<Customer> selectedCustomer = customerService.findById(id);
        if (selectedCustomer.isPresent()){
            Customer customer = selectedCustomer.get();
            customerService.delete(customer);
        }
    }

}
