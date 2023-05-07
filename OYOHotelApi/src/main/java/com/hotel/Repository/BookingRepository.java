package com.hotel.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.Model.Booking;
import com.hotel.Model.Customer;

@Repository
public interface BookingRepository extends JpaRepository<Booking , Integer> {

     List<Booking> findByCustomerAndBookingDateBetween
     (Customer customer , LocalDate date1 , LocalDate date2) ;
}
