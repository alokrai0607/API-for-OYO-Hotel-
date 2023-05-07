package com.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.Model.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
	
}
