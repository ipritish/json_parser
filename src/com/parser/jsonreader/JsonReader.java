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
		Json json = new Json();
		//do manipulation and create proper variable
		return json;	
	}

	private static String stripNewLineAndTab(String jsonString) 
	{
		return jsonString;		
	}
	
	private static void addMapString(Json json, String key, String Value)
	{
		//
	}
	
	private static void addMapJsonObject(Json json, String key, JsonObject jObject)
	{
		//
	}
	
	private static void addMapArray(Json json, String key, JsonArray jArray)
	{
		//
	}

}
