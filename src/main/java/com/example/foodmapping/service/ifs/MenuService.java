package com.example.foodmapping.service.ifs;

import java.util.List;

import com.example.foodmapping.entity.Menu;
import com.example.foodmapping.vo.MenuRequest;
import com.example.foodmapping.vo.MenuResponse;



public interface MenuService {
	
	public MenuResponse addMenu(MenuRequest request);
	
	public List<Menu> getMenu();
	
	public MenuResponse getMenuBystoreName(String storeName);
	


}
