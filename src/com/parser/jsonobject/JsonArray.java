package com.parser.jsonobject;

import java.util.ArrayList;

public class JsonArray {
	
	private ArrayList<Long> longList;
	private ArrayList<Double> doubleList;
	private ArrayList<Boolean> boolList;
	private ArrayList<JsonObject> objectList;
	
	public JsonArray()
	{
		longList = new ArrayList<>();
		doubleList = new ArrayList<>();
		boolList = new ArrayList<>();
		objectList = new ArrayList<JsonObject>();
	}

	public ArrayList<Long> getLongList() {
		return longList;
	}

	public void setLongList(ArrayList<Long> longList) {
		this.longList = longList;
	}

	public ArrayList<Double> getDoubleList() {
		return doubleList;
	}

	public void setDoubleList(ArrayList<Double> doubleList) {
		this.doubleList = doubleList;
	}

	public ArrayList<Boolean> getBoolList() {
		return boolList;
	}

	public void setBoolList(ArrayList<Boolean> boolList) {
		this.boolList = boolList;
	}

	public ArrayList<JsonObject> getObjectList() {
		return objectList;
	}

	public void setObjectList(ArrayList<JsonObject> objectList) {
		this.objectList = objectList;
	} 

}
