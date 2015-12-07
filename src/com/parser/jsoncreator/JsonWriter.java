package com.parser.jsoncreator;

import com.parser.jsonobject.Json;
import com.parser.jsonobject.JsonObject;

public class JsonWriter {
	
	public static void main(String args[])
	{
		Json json = new Json();
		json.getMapString().put("first", "trial");
		json.getMapObjects().put("second", new JsonObject());
		json.getMapObjects().get("second").getMapString().put("insidefirst", "insidesecond");
		
	}
	
	public static void JsonWrite(Json json)
	{
		//write all the string first than
		//iterate through object and separate function object writing
	}

}
