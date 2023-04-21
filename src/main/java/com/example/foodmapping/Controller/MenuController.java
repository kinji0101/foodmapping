package com.example.foodmapping.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodmapping.entity.Menu;
import com.example.foodmapping.service.ifs.MenuService;
import com.example.foodmapping.vo.MenuRequest;
import com.example.foodmapping.vo.MenuResponse;


@RestController
public class MenuController {
	
	@Autowired
	private MenuService service;
	
	@PostMapping("/add_Menu")
	private MenuResponse addMenu(@RequestBody MenuRequest request) {
		return service.addMenu(request);
	}
	
	@PostMapping("/get_Menu")
	private List<Menu> getMenu(){
		return service.getMenu();
	}
	
	

}
