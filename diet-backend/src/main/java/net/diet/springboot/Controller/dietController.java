package net.diet.springboot.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.diet.springboot.model.Menu;
import net.diet.springboot.service.DietService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/diet")
public class dietController {
	private DietService dietService;

	public dietController(DietService dietService) {
		super();
		this.dietService=dietService;
	}
	
	@PostMapping("save")
	public ResponseEntity<Menu> saveMenu(@RequestBody Menu menu){
		return new ResponseEntity<Menu>(dietService.saveMenu(menu),HttpStatus.CREATED);
	}
	
	@GetMapping("get/day")
	public  List<Menu> getDayMenu(@RequestParam(name="date") String date){
		return dietService.getDayMenu(date);
	}
	
	@GetMapping("get/ranking")
	public  List<Map<String, Integer>> getRanking(@RequestParam(name="dateFrom") String dateFrom,
									@RequestParam(name="dateTo") String dateTo){
		return dietService.getRanking(dateFrom, dateTo);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteMenu(@RequestBody Menu menu){
		dietService.deleteMenu(menu);
		return new ResponseEntity<String>("Menu Deleted Successfully!",HttpStatus.OK);
	}
}
