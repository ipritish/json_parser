package com.parser.jsoncreator;

import java.util.Map.Entry;

import com.parser.jsonobject.Json;
import com.parser.jsonobject.JsonArray;
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
		
		for (Entry<String, JsonArray> entry : json.getMapArray().entrySet()) {
		    sb.append("\"" + entry.getKey() + "\""+ ":");
		    JsonArrayWrite(entry.getValue());
		    sb.append(",");
		    //entry.getValue() + "\",");
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
	
	public static void JsonArrayWrite(JsonArray jsonArray) {
		sb.append("[");
		//write all primitives
		//write long
		for (long value : jsonArray.getLongList())
		{
			sb.append(value);
			sb.append(",");
		}
		//write booleans
		for (boolean value : jsonArray.getBoolList())
		{
			sb.append(value ? "true" : "false");
			sb.append(",");
		}
		//write doubles
		for (double value : jsonArray.getDoubleList())
		{
			sb.append(value);
			sb.append(",");
		}
		//write objects
		for (JsonObject value : jsonArray.getObjectList())
		{
			JsonObjectWrite(value);
			//sb.append(",");
		}
		
		for (JsonArray value : jsonArray.getArrayList())
		{
			JsonArrayWrite(value);
			sb.append(",");
		}
		if(jsonArray.getObjectList().size() > 0 ||
				jsonArray.getBoolList().size() > 0||
				jsonArray.getArrayList().size() > 0||
				jsonArray.getDoubleList().size() > 0||
				jsonArray.getLongList().size() > 0)
		{
			sb.setLength(sb.length() - 1);
		}
		
		sb.append("]");
		
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
		
		for (Entry<String, JsonArray> entry : jsonObject.getMapArray().entrySet()) {
			sb.append(",");
			sb.append("\"" + entry.getKey() + "\"" + ":");
			JsonArrayWrite(entry.getValue());
		    sb.append("");	    
		}
		
		//if (jsonObject.getMapArray().size() > 0) {
			//sb.setLength(sb.length() - 1);
		//}
		
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
