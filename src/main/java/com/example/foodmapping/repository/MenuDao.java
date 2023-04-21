package com.example.foodmapping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foodmapping.entity.Menu;
import com.example.foodmapping.entity.Restaurant;
import com.example.foodmapping.entity.RestaurantAndMenu;

@Repository
public interface MenuDao extends JpaRepository <Menu, RestaurantAndMenu>{

	boolean existsByStoreMenu(String storeMenu);

	boolean existsByStoreName(String storeName);

	Optional<Menu> findByStoreName(String storeName);

	Optional<Restaurant> findById();


}
