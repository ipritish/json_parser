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
		json.getMapObjects().put("third", new JsonObject());
		json.getMapObjects().get("third").getMapString().put("insidesecond", "insidesecond");
		json.getMapObjects().get("third").getMapObject().put("insideinsidefirst", new JsonObject());
		json.getMapObjects().get("third").getMapObject().get("insideinsidefirst").getMapString().put("test","test");
		JsonWriter.JsonWrite(json);
		
	}

}
