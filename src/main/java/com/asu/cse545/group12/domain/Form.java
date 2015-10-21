package com.asu.cse545.group12.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Form {
	Map<String, String> map = new HashMap<String, String>();
	Map<String, Object> mapObject = new HashMap<String, Object>();
	

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Map<String, Object> getMapObject() {
		return mapObject;
	}

	public void setMapObject(Map<String, Object> mapObject) {
		this.mapObject = mapObject;
	}
	
	
}