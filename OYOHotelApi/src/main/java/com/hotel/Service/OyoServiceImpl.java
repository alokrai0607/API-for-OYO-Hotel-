package com.hotel.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.Excepction.OYOException;
import com.hotel.Model.Booking;
import com.hotel.Model.Customer;
import com.hotel.Model.Hotel;
import com.hotel.Model.RoomType;
import com.hotel.Model.Status;
import com.hotel.Repository.BookingRepository;
import com.hotel.Repository.CutomerRepository;
import com.hotel.Repository.HotelRepository;
import com.hotel.Repository.RoomTypeRepository;

@Service
public class OyoServiceImpl implements OyoService{

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CutomerRepository cutomerRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Override
    public Customer addCustomer(Customer customer) throws OYOException {
        Optional<Customer> customer1 = cutomerRepository.findById(customer.getCustomerId()) ;
        if(customer1.isPresent()) throw new OYOException("Customer already exist");
        return cutomerRepository.save(customer) ;
    }

    @Override
    public Hotel addHotel(Hotel hotel) throws OYOException {
        if(hotelRepository.findById(hotel.getHotelId()).isPresent()) throw new OYOException("hotel already exist" ) ;
        return hotelRepository.save(hotel);
    }



    @Override
    public boolean makeBooking(int customerId, int roomId, LocalDate bookingDate) throws OYOException {
        Customer customer = cutomerRepository.findById(customerId).orElseThrow(()-> new OYOException("no customer found")) ;
        RoomType roomType = roomTypeRepository.findById(roomId).orElseThrow(()->new OYOException("No roomType found") ) ;
        Booking booking = new Booking();
        booking.setBookingDate(bookingDate);
        booking.setCustomer(customer);
        customer.getBookingList().add(booking) ;
        booking.setRoom(roomType);
        roomType.getBookingList().add(booking) ;
        booking.setStatus(Status.PENDING);

        bookingRepository.save(booking);
        return true;

    }

    @Override
    public RoomType addRoomType(RoomType roomType, int hotelId) throws OYOException {

        Optional<RoomType> roomType1 = roomTypeRepository.findById(roomType.getRoomTypeId());
        if(roomType1.isPresent()) throw new OYOException("roomtype already exist") ;
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new OYOException("No hotel exist")) ;
        roomType.setHotel(hotel);
        hotel.getRoomTypes().add(roomType) ;
        return roomTypeRepository.save(roomType) ;
    }

    @Override
    public boolean updateBooking(int customerId, String city , int newRoomId) throws OYOException {

        Customer customer = cutomerRepository.findById(customerId).orElseThrow(()-> new OYOException("no customer found")) ;
        RoomType roomType1 = roomTypeRepository.findById(newRoomId).orElseThrow(()->new OYOException("No roomType found") ) ;
        List<Hotel> hotels = hotelRepository.findByCity(city) ;
        if(hotels.isEmpty()) throw new OYOException("no hotel found") ;
        List<RoomType> roomTypes = hotels.get(0).getRoomTypes() ;
        if(roomTypes.isEmpty()) throw new OYOException("No room available") ;
        List<Booking> list = customer.getBookingList().stream().filter(a-> a.getRoom().getRoomTypeId() == newRoomId).toList();
        if(list.isEmpty()) throw new OYOException("No booking found") ;
        Booking booking = list.get(0) ;
        booking.setRoom(roomType1);
        bookingRepository.save(booking) ;
        return  true ;

    }

    @Override
    public List<Booking> getBooking(int customerId, LocalDate day1 , LocalDate day2) throws OYOException {
        Customer customer = cutomerRepository.findById(customerId).orElseThrow(()-> new OYOException("no customer exist"));
        List<Booking> bookingList = bookingRepository.findByCustomerAndBookingDateBetween(customer,day1,day2) ;
        if (bookingList.isEmpty()) throw new OYOException("No booking found") ;
        return bookingList;
    }

    @Override
    public List<Hotel> getHotelList() throws OYOException {
        List<Hotel> hotels = hotelRepository.findAll();
        if(hotels.isEmpty()) throw new OYOException("No hotel is present");
        return hotels;
    }
}
