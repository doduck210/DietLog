package net.diet.springboot.service;

import java.util.List;
import java.util.Map;

import net.diet.springboot.model.Menu;

public interface DietService {
	Menu saveMenu(Menu menu);
	
	List<Menu> getDayMenu(String date);
	List<Map<String, Integer>> getRanking(String dateFrom,String dateTo);
	
	void deleteMenu(Menu menu);
}
