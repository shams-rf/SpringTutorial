package com.shamsuddin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    // Customer repository to be injected
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Main method to run the program
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    // Method to get customers from database
    @GetMapping
    public List<Customer> getCustomers() {

        return customerRepository.findAll();
    }

    // Record used to specify values to be inserted into customer entity
    // Injected into addCustomer method
    record NewCustomerRequest(String email, String name, int age) {

    }

    // Method to add customer to database
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {

        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    // Method to delete customer from database
    // Customer id passed into url path
    @DeleteMapping("{customerID}")
    public void deleteCustomer(@PathVariable("customerID") Integer id) {

        customerRepository.deleteById(id);
    }

    // Record used to specify values to be updated
    // Injected into updateCustomer method
    record UpdateCustomerRequest(String email, String name, int age) {

    }

    // Method used to update existing customer entity in database
    // Customer id passed into url path
    @PutMapping("{customerID}")
    public void updateCustomer(@PathVariable("customerID") Integer id, @RequestBody UpdateCustomerRequest request) {

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }
}
