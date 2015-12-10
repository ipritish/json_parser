package com.parser.jsonobject;

import java.util.HashMap;
import java.util.Map;

public class Json {
	
	private Map<String, String> mapString;
	private Map<String, JsonObject> mapObjects;
	private Map<String, JsonArray> mapArray;
	
	public Json()
	{
		mapString = new HashMap<String,String>();
		mapObjects = new HashMap<String,JsonObject>();
		mapArray = new HashMap<String,JsonArray>();
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
	public Map<String, JsonArray> getMapArray() {
		return mapArray;
	}
	public void setMapArray(Map<String, JsonArray> mapArray) {
		this.mapArray = mapArray;
	}
	
	

}
