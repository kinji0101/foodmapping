package com.example.foodmapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foodmapping.entity.Restaurant;

@Repository
public interface RestaurantDao  extends JpaRepository<Restaurant, String>{

	List<Restaurant> findByCity(String city);

	List<Restaurant> findContainingByStarLessThanEqualOrderByStarDesc(int star);
	
	List<Restaurant> findByPointGreaterThanEqualOrderByPointDesc(Integer point);

	List<Restaurant> findByAllId(String id);
	
	
	


	

}
