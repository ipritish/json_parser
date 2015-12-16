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
	
	private static int openBraces = 0;
	private static int closedBraces = 0;
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
		jsonString = jsonString.substring(1,jsonString.length() - 1);
		System.out.println(jsonString);
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
			
			//add only outside key check for that
			String subString = jsonString.substring(0, jsonString.indexOf(key));
			System.out.println(subString);
			// count number of open braces it should be diff of 1 for it to be outside
			keys.add(key);
			
			
		}
		keys.remove(keys.size() - 1);
		for (String val : keys)
		{
			System.out.println(val);
		}
		String totalValue = "";
		String exampleString = jsonString.substring(jsonString.indexOf('{', 1));
		//System.out.println(exampleString);
		for (char val : exampleString.toCharArray())
		{
			if(val == '{')
			{
				openBraces++;
				//System.out.println(val + "\t fount it");
			}
			if(val == '}')
			{
				closedBraces++;
				//System.out.println(val + "\t fount it");
			}
			totalValue += val;
			if(openBraces == closedBraces)
				break;
			
		}
		//System.out.println("object found" + "\t" +(openBraces - 1));
		//System.out.println(totalValue);
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
