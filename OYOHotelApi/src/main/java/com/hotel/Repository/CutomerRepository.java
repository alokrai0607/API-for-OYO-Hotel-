package com.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.Model.Customer;

@Repository
public interface CutomerRepository extends JpaRepository<Customer, Integer> {

}
