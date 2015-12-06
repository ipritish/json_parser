package com.parser.jsonobject;

import java.util.ArrayList;

public class JsonFile {
	
	private ArrayList<JsonObject> objectlist;

	public ArrayList<JsonObject> getObjectlist() {
		return objectlist;
	}

	public void setObjectlist(ArrayList<JsonObject> objectlist) {
		this.objectlist = objectlist;
	}
	
	public JsonFile()
	{
		setObjectlist(new ArrayList<JsonObject>());
	}

}
