package com.parser.jsoncreator;

import java.util.Map.Entry;

import com.parser.jsonobject.Json;
import com.parser.jsonobject.JsonObject;

public class JsonWriter {
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void JsonWrite(Json json)
	{
		sb.append("{");
		//write all the string first than
		//iterate through object and separate function object writing
		//Iterator<String> stringObject = json.getMapString().
		for (Entry<String, String> entry : json.getMapString().entrySet()) {
		    sb.append("\"" + entry.getKey() + "\""+ ":\"" + entry.getValue() + "\",");
		}
		
		for (Entry<String, JsonObject> entry : json.getMapObjects().entrySet()) {
		    //System.out.println("Key : " + entry.getKey() + "Value: ");
			sb.append("\"" + entry.getKey() + "\"" + ":");
		    JsonObjectWrite(entry.getValue());
		    
		    		    
		}
		if (sb.length() > 0) 
		{
			sb.setLength(sb.length() - 1);
		}
		sb.append("}");
		System.out.println(sb.toString());
		
	}
	
	public static void JsonObjectWrite(JsonObject jsonObject)
	{
		sb.append("{");
		
		for (Entry<String, String> entry : jsonObject.getMapString().entrySet()) {
		    //System.out.println("Key : " + entry.getKey() + "Value: " + entry.getValue());
			sb.append("\"" + entry.getKey() + "\""+ ":\"" + entry.getValue() + "\",");
			
			
		}
		// Readable version
		if (jsonObject.getMapString().size() > 0) {
		   sb.setLength(sb.length() - 1);
		}
		//sb.append("\n");
		
		for (Entry<String, JsonObject> entry : jsonObject.getMapObject().entrySet()) {
			sb.append(",");
			sb.append("\"" + entry.getKey() + "\"" + ":");
			JsonObjectWrite(entry.getValue());
		    sb.append("");	    
		}
		// Readable version
		if (jsonObject.getMapObject().size() > 0) {
			sb.setLength(sb.length() - 1);
		}
		sb.append("},");
	}

}
