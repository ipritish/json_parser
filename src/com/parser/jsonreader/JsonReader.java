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
	
	private static ArrayList<String> keys = new ArrayList<String>();
	
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
		//System.out.println(jsonString);
		jsonString = stripNewLineAndTab(jsonString);
		//System.out.println(jsonString);
		Json json = new Json();
		//do manipulation and create proper variable
		String[] splitValues = jsonString.split(":");
		for (String var : splitValues)
		{
			//System.out.println(var);
			
			String[] getKeys = var.split(",");
			String key = getKeys[getKeys.length - 1];
			//System.out.println(key);
			//String revVar = new StringBuilder(var).reverse().toString();
			key = key.replaceAll("\"", "")
					.replaceAll("\\{", "").replaceAll("\\}","").replaceAll("\\[", "")
					.replaceAll("\\]", "");
			
			// count number of open braces it should be diff of 1 for it to be outside
			keys.add(key);
			
			
		}
		keys.remove(keys.size() - 1);
		//System.out.println(exampleString);
		char[] val = jsonString.toCharArray();
		for (int i=0 ; i<val.length ; i++)
		{	
			switch(val[i])
			{
				case '{':
					String restObject = jsonString.substring(i);
					System.out.println(restObject);
					break;
				case '"':
					break;
				case '}':
					break;
				case '[':
					break;
				case ']':
					break;
				case ':':
					break;
				
			}
			
		}
		return json;	
	}

	private static String stripNewLineAndTab(String jsonString) 
	{
		return jsonString.replaceAll("[\\s]", "");		
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
