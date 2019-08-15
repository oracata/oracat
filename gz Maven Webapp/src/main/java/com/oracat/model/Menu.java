package com.oracat.model;

public class Menu{
	
	private Integer menu_id;
	private Integer up_id  ;
	private String menu_name;  
	private String url;  
	private Integer level;

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	
	public Integer getMenu_id() {
		return this.menu_id;
	}
	
	public void setUp_id(Integer up_id) {
		this.up_id = up_id;
	}
	
	public Integer getUp_id() {
		return this.up_id;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getLevel() {
		return this.level;
	}
	

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	
	public String getMenu_name() {
		return this.menu_name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getUrl() {
		return this.url;
	}

 


}