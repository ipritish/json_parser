package com.parser.jsonreader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.parser.jsonobject.Json;
import com.parser.jsonobject.JsonArray;
import com.parser.jsonobject.JsonObject;

public class JsonReader {
	
	public static Json parse(String filePath)
	{
		//jsonString
		Charset charset = Charset.forName("US-ASCII");
		List<String> allLines = new ArrayList<String>();
		Path file = FileSystems.getDefault().getPath(filePath);
		try {
				allLines = Files.readAllLines(file, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String jsonString = new String("");
		for (String var : allLines)
		{
			jsonString+=var;
		}
		jsonString = stripNewLineAndTab(jsonString);
		System.out.println(jsonString);
		jsonString = jsonString.replaceAll("[\\s]", "");
		Json json = new Json();
		System.out.println(jsonString);
		//do manipulation and create proper variable
		String[] splitValues = jsonString.split(":");
		for(String val : splitValues)
		{
			System.out.println(val);
		}
		return json;	
	}

	private static String stripNewLineAndTab(String jsonString) 
	{
		return jsonString;		
	}
	
	//these will iterate to outer loop
	private static void addMapString(Json json, String key, String Value)
	{
		//
	}
	
	private static void addMapJsonObject(Json json, String key, String vObject)
	{
		//parse string and assign to object here
		JsonObject jObject = new JsonObject();
		json.getMapObjects().put(key, jObject);
	}
	
	private static void addMapArray(Json json, String key, String vArray)
	{
		//parse string and assign to objct here
		JsonArray jArray = new JsonArray();
		json.getMapArray().put(key, jArray);
	}
	
	private static void addArrayToObject(JsonObject jObject, JsonArray jArray, String key)
	{
		jObject.getMapArray().put(key, jArray);
	}
	
	private static void addObjectToArray(JsonArray jArray, JsonObject jObject)
	{
		jArray.getObjectList().add(jObject);
	}

}
