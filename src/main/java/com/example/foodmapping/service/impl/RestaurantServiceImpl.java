package com.example.foodmapping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.foodmapping.entity.Menu;
import com.example.foodmapping.entity.Restaurant;
import com.example.foodmapping.repository.MenuDao;
import com.example.foodmapping.repository.RestaurantDao;
import com.example.foodmapping.service.ifs.RestaurantService;
import com.example.foodmapping.vo.RestaurantRequest;
import com.example.foodmapping.vo.RestaurantResponse;
import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.vo.BankRequest;
import com.example.java_demo_test.vo.BankResponse;


@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantDao restaurantDao;
	

	@Override
	public RestaurantResponse addRestaurant(RestaurantRequest request) {
		List<Restaurant> errorInfoList = new ArrayList<>();
		List<Restaurant> infoList = request.getRestaurantList();
		for (Restaurant item : infoList) {
			if(!StringUtils.hasText(item.getId()) || !StringUtils.hasText(item.getCity())) {
				return new RestaurantResponse("Add Restaurant Info error");
			}
			if(restaurantDao.existsById(item.getId())) {
				errorInfoList.add(item);
			}
			if(1 > request.getStar() || 5 < request.getStar()) {
				return new RestaurantResponse("Add Restaurant Info error");				
			}
			
		}
		
		if(!errorInfoList.isEmpty()) {
			return new RestaurantResponse("店家已存在");
		}
		
		restaurantDao.saveAll(infoList);
		return new RestaurantResponse(infoList, "Add Restaurant Info Successful");
	}

	@Override
	public List<Restaurant> getRestaurant() {
		return restaurantDao.findAll();		
	}

	@Override
	public RestaurantResponse getRestaurantById(String id) {
		if(!StringUtils.hasText(id)) {
			return new RestaurantResponse("id 不得為空");
	}
		Optional<Restaurant> op = restaurantDao.findById(id);
		if(op.isPresent()) {
			return new RestaurantResponse(op.get());
		}
		return new RestaurantResponse("查無資料");
	}

	
	@Override
	public List<Restaurant> getRestaurantByCity(String city) {
		return restaurantDao.findByCity(city);
	}

	
	@Override
	public List<Restaurant> getRestaurantByid(String id) {
		return restaurantDao.findByAllId(id);
	}
	
	@Override
	public List<Restaurant> getRestaurantByStarLessThanEqualOrderByStarDesc(int star) {
		return restaurantDao.findContainingByStarLessThanEqualOrderByStarDesc(star);
	}

	
	@Override
	public RestaurantResponse updateRestaurant(RestaurantRequest RestaurantupdateRequest) {
		String reqName = RestaurantupdateRequest.getId();
		String reqCity = RestaurantupdateRequest.getCity();
		String reqNewCity = RestaurantupdateRequest.getCity();

		if (!StringUtils.hasText(reqName) || !StringUtils.hasText(reqCity) || !StringUtils.hasText(reqNewCity)) {
			return new RestaurantResponse("修改內容不能為空");
		}

		Optional<Restaurant> op = restaurantDao.findById(reqName);
		if (!op.isPresent()) {
			return new RestaurantResponse(reqName, "沒有此店家");
		}
		Restaurant restaurant = op.get();
		if (!reqCity.equals(restaurant.getCity())) {
			return new RestaurantResponse(reqName, "該店家不在此地區");
		}
		restaurant.setCity(reqNewCity);
		restaurantDao.save(restaurant);
		return new RestaurantResponse(reqName, "修改城市成功");
	}
	
	@Override
	public RestaurantResponse UpdateRestaurantStar(RestaurantRequest request) {
		if (!StringUtils.hasText(request.getId()) || !StringUtils.hasText(request.getCity())) {
			return new RestaurantResponse("資料不能為空");
		}
		Optional<Restaurant> op = restaurantDao.findById(request.getId());
		if (!op.isPresent()) {
			return new RestaurantResponse(request.getId(), "沒有此店家");
		}
		Restaurant eatMap = op.get();
		if (!request.getCity().equals(eatMap.getCity())) {
			return new RestaurantResponse(request.getId(), "該店家不在此地區");
		}
		var list = restaurantDao.findByAllId(request.getId());
		int sum = 0;
		int star = 0;
		for (int limit = 0; limit <= 2; limit++) {
			Restaurant item = list.get(limit);
			sum += item.getStar();
			if (limit == list.size() - 1) {
				star = sum / list.size();
				break;
			}
		}
		eatMap.setStar(star);
		restaurantDao.save(eatMap);
		return new RestaurantResponse(request.getId(), "修改評分成功");

	}
	



	









}
