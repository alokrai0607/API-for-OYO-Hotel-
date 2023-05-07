package com.hotel.Service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.Excepction.OYOException;
import com.hotel.Model.Booking;
import com.hotel.Model.Customer;
import com.hotel.Model.Hotel;
import com.hotel.Model.RoomType;

public interface OyoService {

	Customer addCustomer(Customer customer) throws OYOException;

	Hotel addHotel(Hotel hotel) throws OYOException;

	boolean makeBooking(int customerId, int roomId, LocalDate bookingDate) throws OYOException;

	RoomType addRoomType(RoomType roomType, int hotelId) throws OYOException;

	boolean updateBooking(int customerId, String city, int newRoomId) throws OYOException;

	List<Booking> getBooking(int customerId, LocalDate day1, LocalDate day2) throws OYOException;

	List<Hotel> getHotelList() throws OYOException;
}
