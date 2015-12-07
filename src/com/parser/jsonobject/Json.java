package com.parser.jsonobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Json {
	
	private Map<String, String> mapString;
	private Map<String, JsonObject> mapObjects;
	
	public Json()
	{
		mapString = new HashMap<String,String>();
		mapObjects = new HashMap<String,JsonObject>();
	}
	public Map<String, String> getMapString() {
		return mapString;
	}
	public void setMapString(Map<String, String> mapString) {
		this.mapString = mapString;
	}
	public Map<String, JsonObject> getMapObjects() {
		return mapObjects;
	}
	public void setMapObjects(Map<String, JsonObject> mapObjects) {
		this.mapObjects = mapObjects;
	}
	
	

}
