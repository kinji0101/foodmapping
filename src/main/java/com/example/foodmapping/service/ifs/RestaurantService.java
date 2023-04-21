package com.example.foodmapping.service.ifs;

import java.util.List;

import com.example.foodmapping.entity.Restaurant;
import com.example.foodmapping.vo.RestaurantRequest;
import com.example.foodmapping.vo.RestaurantResponse;

public interface RestaurantService {
	
	public RestaurantResponse addRestaurant(RestaurantRequest request);
	
	public List<Restaurant> getRestaurant();
	 
	public RestaurantResponse getRestaurantById(String id);	
	
	List<Restaurant> getRestaurantByStarLessThanEqualOrderByStarDesc(int star);

	List<Restaurant> getRestaurantByCity(String city);

	RestaurantResponse updateRestaurant(RestaurantRequest RestaurantupdateRequest);

	List<Restaurant> getRestaurantByid(String id);

	RestaurantResponse UpdateRestaurantStar(RestaurantRequest request);

	
	

	

}
