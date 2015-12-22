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
		//remove first and  last string here as well
		jsonString = jsonString.substring(1, jsonString.lastIndexOf('}'));
		//System.out.println(jsonString);
		char[] val = jsonString.toCharArray();
		for (int i=0 ; i<val.length;)
		{	
			switch(val[i])
			{
				case '{':
					String restObject = jsonString.substring(i);
					String key = jsonString.substring(0,jsonString.indexOf(restObject));
					//System.out.println(restObject);
					key = key.substring(key.lastIndexOf(',')+1);
					key = key.replaceAll("[//:]", "").replace("\"","").trim();
					System.out.println(key);
					System.out.println(restObject);
					String tempRemain = addMapJsonObject(json,key,restObject);
					//System.out.println(tempRemain);
					i = jsonString.length() - tempRemain.length();
					break;
				case '"':
					break;
				case '}':
					break;
				case '[':
					String arrObject = jsonString.substring(i);
					String arrkey = jsonString.substring(0,jsonString.indexOf(arrObject));
					arrkey = arrkey.substring(arrkey.lastIndexOf(',')+1).trim();
					arrkey = arrkey.replaceAll("[//:]", "").replace("\"","").trim();
					System.out.println(arrkey);
					System.out.println(arrObject);
					String temparrRemain = addMapArray(json,arrkey,arrObject);
					i = jsonString.length() - temparrRemain.length();
					break;
				case ']':
					break;
				case ':':
					break;
				
			}
			i++;
				
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
	
	private static String addMapJsonObject(Json json, String key, String vObject)
	{
		//parse string and assign to object here
		
		int beginIndex = 0;
		int endIndex = 0;
		//System.out.println(vArray);
		String objectTobeAdded = "";
		for (char val : vObject.toCharArray())
		{
			if(val == '{')
				beginIndex++;
			if(val == '}')
				endIndex++;
			objectTobeAdded += val;
			if(beginIndex == endIndex)
				break;
		}
		System.out.println(objectTobeAdded);
		String restObject = vObject.substring(objectTobeAdded.length());
		JsonObject jObject = new JsonObject();
		json.getMapObjects().put(key, jObject);
		return restObject;
	}
	
	private static String addMapArray(Json json, String key, String vArray)
	{
		//parse string and assign to object here
		int beginIndex = 0;
		int endIndex = 0;
		//System.out.println(vArray);
		String arrayTobeAdded = "";
		for (char val : vArray.toCharArray())
		{
		
		
				if(val == '[')
					beginIndex++;
				if(val == ']')
					endIndex++;
				arrayTobeAdded += val;
				
				if(beginIndex == endIndex)
					break;
		}
		System.out.println(arrayTobeAdded);
		String restObject = vArray.substring(arrayTobeAdded.length());
		JsonArray jArray = new JsonArray();
		json.getMapArray().put(key, jArray);
		return restObject;
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
