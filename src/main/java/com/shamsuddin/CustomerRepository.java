package com.shamsuddin;

import org.springframework.data.jpa.repository.JpaRepository;

// Customer repository interface that manages the data in the application
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
