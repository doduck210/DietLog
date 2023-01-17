package net.diet.springboot.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.diet.springboot.model.CompositKeys;
import net.diet.springboot.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, CompositKeys>{
	List<Menu> findByDate(String date);
	
	@Query(value = "select menu, count(*) as cnt\n"
			+ "from diet.menu\n"
			+ "where date >= :dateFrom\n"
			+ "	and date <= :dateTo\n"
			+ "group by menu\n"
			+ "having cnt>1\n"
			+ "order by cnt desc"
			, nativeQuery=true)
    List<Map<String, Integer>> menuRanking(@Param("dateFrom") String dateFrom
    										, @Param("dateTo") String dateTo);
}
