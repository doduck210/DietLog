package net.diet.springboot.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class CompositKeys implements Serializable {	
	private String date;
	private String time;
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
