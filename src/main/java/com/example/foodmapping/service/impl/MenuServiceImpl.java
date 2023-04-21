package com.example.foodmapping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.foodmapping.entity.Menu;
import com.example.foodmapping.repository.MenuDao;
import com.example.foodmapping.repository.RestaurantDao;
import com.example.foodmapping.service.ifs.MenuService;
import com.example.foodmapping.vo.MenuRequest;
import com.example.foodmapping.vo.MenuResponse;
import com.example.foodmapping.vo.RestaurantRequest;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDao menuDao;

	@Override
	public MenuResponse addMenu(MenuRequest request) {
		List<Menu> errorInfoList = new ArrayList<>();
		List<Menu> infoList = request.getMenuList();
		for(Menu item : infoList) {
			if(!StringUtils.hasText(item.getStoreMenu())||!StringUtils.hasText(item.getStoreName())) {
				return new MenuResponse("Add Menu Info error");
			}
			if(menuDao.existsByStoreMenu(item.getStoreMenu())) {
				errorInfoList.add(item);
			}
			if(menuDao.existsByStoreName(item.getStoreName())) {
				errorInfoList.add(item);
			}
			if(1 > request.getMenustar() || 5 < request.getMenustar()) {
				return new MenuResponse("Add Menu Info error");		
			}
			
		}
		if(!errorInfoList.isEmpty()) {
			return new MenuResponse("店家已存在");	
		}
		menuDao.saveAll(infoList);
		return new MenuResponse(infoList,"Add Menu Info Successful");
	}

	@Override
	public List<Menu> getMenu() {		
		return menuDao.findAll();
	}

	@Override
	public MenuResponse getMenuBystoreName(String storeName) {
		if(!StringUtils.hasText(storeName)) {
			return new MenuResponse("storeName 不得為空");
	}
		Optional<Menu> op = menuDao.findByStoreName(storeName);
		if(op.isPresent()) {
			return new MenuResponse(op.get());
		}
		return new MenuResponse("查無資料");
	}

	

	





	

}
