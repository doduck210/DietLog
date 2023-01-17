package net.diet.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@IdClass(CompositKeys.class)
@Table(name="menu")
public class Menu {
	@Id
	@JsonProperty("date")
	private String date;
	
	@Id
	@JsonProperty("time")
	private String time;
	
	@Id
	@JsonProperty("menu")
	private String menu;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	
}
