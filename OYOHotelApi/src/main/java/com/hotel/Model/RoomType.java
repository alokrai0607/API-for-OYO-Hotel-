package com.hotel.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomTypeId;
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	@JsonIgnore
	private Hotel hotel;
	@NotBlank
	private String name;
	@Enumerated(EnumType.STRING)
	private Type type;
	@NotBlank
	private String description;
	@Min(1)
	@Max((25))
	private int capacity;
	@Min(100)
	private double price;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "room")
	private List<Booking> bookingList;

	@Override
	public String toString() {
		return "RoomType{" + "roomTypeId=" + roomTypeId + ", hotel=" + hotel + ", name='" + name + '\''
				+ ", description='" + description + '\'' + ", capacity=" + capacity + ", price=" + price + '}';
	}
}
