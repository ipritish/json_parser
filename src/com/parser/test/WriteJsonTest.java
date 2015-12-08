package com.parser.test;

import com.parser.jsoncreator.JsonWriter;
import com.parser.jsonobject.Json;
import com.parser.jsonobject.JsonObject;

public class WriteJsonTest {
	
	public static void main(String args[])
	{
		Json json = new Json();
		json.getMapString().put("first", "trial");
		json.getMapObjects().put("second", new JsonObject());
		json.getMapObjects().get("second").getMapString().put("insidefirst", "insidesecond");
		JsonWriter.JsonWrite(json);
		
	}

}
