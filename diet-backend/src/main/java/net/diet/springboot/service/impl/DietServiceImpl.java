package net.diet.springboot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.diet.springboot.model.CompositKeys;
import net.diet.springboot.model.Menu;
import net.diet.springboot.repository.MenuRepository;
import net.diet.springboot.service.DietService;

@Service
public class DietServiceImpl implements DietService {
	
	private MenuRepository menuRepository;
	
	public DietServiceImpl(MenuRepository menuRepository) {
		super();
		this.menuRepository = menuRepository;
	}

	@Override
	public Menu saveMenu(Menu menu) {
		return menuRepository.save(menu);
		
	}

	@Override
	public List<Menu> getDayMenu(String date) {
		List<Menu> menu=menuRepository.findByDate(date);
		return menu;
	}
	
	@Override
	public List<Map<String, Integer>> getRanking(String dateFrom, String dateTo) {
		List<Map<String, Integer>> ranks=menuRepository.menuRanking(dateFrom, dateTo);
		return ranks;
	}

	@Override
	public void deleteMenu(Menu menu) {
		CompositKeys m = new CompositKeys();
		m.setDate(menu.getDate());
		m.setTime(menu.getTime());
		m.setMenu(menu.getMenu());
		menuRepository.deleteById(m);
	}

}
