package com.parser.jsoncreator;

import java.util.Map.Entry;

import com.parser.jsonobject.Json;
import com.parser.jsonobject.JsonObject;

public class JsonWriter {
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void JsonWrite(Json json)
	{
		sb.append("{\n");
		//write all the string first than
		//iterate through object and separate function object writing
		//Iterator<String> stringObject = json.getMapString().
		for (Entry<String, String> entry : json.getMapString().entrySet()) {
		    sb.append("\t\"" + entry.getKey() + "\""+ "\t:\t\"" + entry.getValue() + "\"\n");
		    
		    
		}
		
		for (Entry<String, JsonObject> entry : json.getMapObjects().entrySet()) {
		    //System.out.println("Key : " + entry.getKey() + "Value: ");
			sb.append("\t\"" + entry.getKey() + "\"" + "\t:\t");
		    JsonObjectWrite(entry.getValue());
		    		    
		}
		sb.append("}");
		System.out.println(sb.toString());
		
	}
	
	public static void JsonObjectWrite(JsonObject jsonObject)
	{
		sb.append("{\n");
		
		for (Entry<String, String> entry : jsonObject.getMapString().entrySet()) {
		    //System.out.println("Key : " + entry.getKey() + "Value: " + entry.getValue());
			sb.append("\t\"" + entry.getKey() + "\""+ "\t:\t\"" + entry.getValue() + "\"\n");
		    
		}
		sb.append("}\n");
		
		for (Entry<String, JsonObject> entry : jsonObject.getMapObject().entrySet()) {
			JsonObjectWrite(entry.getValue());
		    		    
		}
	}

}
