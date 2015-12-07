package com.parser.jsoncreator;

import java.util.Map.Entry;

import com.parser.jsonobject.Json;
import com.parser.jsonobject.JsonObject;

public class JsonWriter {
	
	public static void main(String args[])
	{
		Json json = new Json();
		json.getMapString().put("first", "trial");
		json.getMapObjects().put("second", new JsonObject());
		json.getMapObjects().get("second").getMapString().put("insidefirst", "insidesecond");
		JsonWrite(json);
		
	}
	
	public static void JsonWrite(Json json)
	{
		//write all the string first than
		//iterate through object and separate function object writing
		//Iterator<String> stringObject = json.getMapString().
		for (Entry<String, String> entry : json.getMapString().entrySet()) {
		    System.out.println("Key : " + entry.getKey() + "Value: " + entry.getValue());
		    
		    
		}
		
		for (Entry<String, JsonObject> entry : json.getMapObjects().entrySet()) {
		    System.out.println("Key : " + entry.getKey() + "Value: ");
		    JsonObjectWrite(entry.getValue());
		    		    
		}
		
	}
	
	public static void JsonObjectWrite(JsonObject jsonObject)
	{
		for (Entry<String, String> entry : jsonObject.getMapString().entrySet()) {
		    System.out.println("Key : " + entry.getKey() + "Value: " + entry.getValue());
		    
		    
		}
		
		//for (Entry<String, JsonObject> entry : jsonObject.getMapObject().entrySet()) {
		    //System.out.println("Key : " + entry.getKey() + "Value: ");
		    		    
		//}
	}

}