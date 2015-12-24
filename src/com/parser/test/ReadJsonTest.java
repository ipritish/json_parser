package com.parser.test;

import com.parser.jsoncreator.JsonWriter;
import com.parser.jsonobject.Json;
import com.parser.jsonreader.JsonReader;

public class ReadJsonTest {
	
	public static void main(String[] args)
	{
		Json json = JsonReader.parse("input.json");
		//System.out.println(json.toString());
		JsonWriter.JsonWrite(json, "test.json");
	}

}
