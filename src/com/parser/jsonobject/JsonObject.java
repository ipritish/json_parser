package com.parser.jsonobject;

import java.util.HashMap;
import java.util.Map;

public class JsonObject {
	
	private Map<String,String> mapString;
	private Map<String,JsonObject> mapObject;
	private Map<String,JsonArray> mapArray;
	public JsonObject()
	{
		setMapString(new HashMap<String,String>());
		setMapObject(new HashMap<String,JsonObject>());
		setMapArray(new HashMap<String,JsonArray>());
	}
	public Map<String,String> getMapString() {
		return mapString;
	}
	public void setMapString(Map<String,String> mapString) {
		this.mapString = mapString;
	}
	public Map<String,JsonObject> getMapObject() {
		return mapObject;
	}
	public void setMapObject(Map<String,JsonObject> mapObject) {
		this.mapObject = mapObject;
	}
	public Map<String,JsonArray> getMapArray() {
		return mapArray;
	}
	public void setMapArray(Map<String,JsonArray> mapArray) {
		this.mapArray = mapArray;
	}
}
