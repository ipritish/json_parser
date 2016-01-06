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
		//System.out.println(jsonString);
		jsonString = stripNewLineAndTab(jsonString);
		//System.out.println(jsonString);
		Json json = new Json();
		//do manipulation and create proper variable
		//remove first and  last string here as well
		jsonString = jsonString.substring(1,jsonString.lastIndexOf('}'));
		System.out.println(jsonString);
		char[] val = jsonString.toCharArray();
		System.out.println(val.length);
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
					String tempRemain = addMapJsonObject(json,key,restObject);
					i = jsonString.length() - tempRemain.length();
					break;
				case '"':
					String restString = jsonString.substring(i);
					if(stringObject(restString))
					{
						if (restString.contains(","))
						{
							String mapString = restString.substring(0,restString.indexOf(','));
							String[] tempArray = mapString.split(":");
							String keyString = tempArray[0];
							keyString = keyString.replaceAll("[//:]", "").replace("\"","").trim();
							String valString = tempArray[1];
							valString = valString.replaceAll("[//:]", "").replace("\"","").trim();
							addMapString(json, keyString, valString);
							i = i + mapString.length();
						}
						else if(restString.contains(":"))
						{
							String[] tempArray = restString.split(":");
							String keyString = tempArray[0];
							keyString = keyString.replaceAll("[//:]", "").replace("\"","").trim();
							String valString = tempArray[1];
							valString = valString.replaceAll("[//:]", "").replace("\"","").trim();
							addMapString(json, keyString, valString);
							i = i + restString.length();
						}
					}
					break;
				case '}':
					break;
				case '[':
					String arrObject = jsonString.substring(i);
					String arrkey = jsonString.substring(0,jsonString.indexOf(arrObject));
					arrkey = arrkey.substring(arrkey.lastIndexOf(',')+1).trim();
					arrkey = arrkey.replaceAll("[//:]", "").replace("\"","").trim();
					String temparrRemain = addMapArray(json,arrkey,arrObject);
					i = jsonString.length() - temparrRemain.length();
					break;
				case ']':
					break;
				case ':':
					break;
				
			}
			System.out.println(i);
			i++;
				
		}
		return json;	
	}

	private static boolean stringObject(String restString) 
	{
		return (restString.substring(restString.indexOf(':', 1) + 1).charAt(0) == '"');
	}

	private static String stripNewLineAndTab(String jsonString) 
	{
		return jsonString.replaceAll("[\\s]", "");		
	}
	
	//these will iterate to outer loop
	private static void addMapString(Json json, String key, String value)
	{
		json.getMapString().put(key, value);
	}
	
	private static void addMapStringToObject(JsonObject json, String key, String value)
	{
		json.getMapString().put(key, value);
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
		objectTobeAdded = objectTobeAdded.substring(1,objectTobeAdded.lastIndexOf('}'));
		char val[] = objectTobeAdded.toCharArray();
		for (int i=0 ; i<val.length;)
		{	
			switch(val[i])
			{
				case '{':
					String reObject = objectTobeAdded.substring(i);
					String innerkey = objectTobeAdded.substring(0,objectTobeAdded.indexOf(reObject));
					//System.out.println(restObject);
					innerkey = innerkey.substring(innerkey.lastIndexOf(',')+1);
					innerkey = innerkey.replaceAll("[//:]", "").replace("\"","").trim();
					String tempRemain = addMapJsonObjectIterator(jObject,innerkey,reObject);
					i = objectTobeAdded.length() - tempRemain.length();
					break;
				case '"':
					String restString = objectTobeAdded.substring(i);
					if(stringObject(restString))
					{
						if (restString.contains(","))
						{
							String mapString = restString.substring(0,restString.indexOf(','));
							String[] tempArray = mapString.split(":");
							String keyString = tempArray[0];
							keyString = keyString.replaceAll("[//:]", "").replace("\"","").trim();
							String valString = tempArray[1];
							valString = valString.replaceAll("[//:]", "").replace("\"","").trim();
							addMapStringToObject(jObject, keyString, valString);
							i = i + mapString.length();
						}
						else if(restString.contains(":"))
						{
							String[] tempArray = restString.split(":");
							String keyString = tempArray[0];
							keyString = keyString.replaceAll("[//:]", "").replace("\"","").trim();
							String valString = tempArray[1];
							valString = valString.replaceAll("[//:]", "").replace("\"","").trim();
							addMapStringToObject(jObject, keyString, valString);
							i = i + restString.length();
						}
					}
					break;
				case '}':
					break;
				case '[':
					String arrObject = objectTobeAdded.substring(i);
					String arrkey = objectTobeAdded.substring(0,objectTobeAdded.indexOf(arrObject));
					arrkey = arrkey.substring(arrkey.lastIndexOf(',')+1).trim();
					arrkey = arrkey.replaceAll("[//:]", "").replace("\"","").trim();
					String temparrRemain = addMapArrayToObject(jObject,arrkey,arrObject);
					i = objectTobeAdded.length() - temparrRemain.length();
					break;
				case ']':
					break;
				case ':':
					break;
				
			}
			System.out.println(i);
			i++;
				
		}
		
		return restObject;
	}
	
	private static String addMapJsonObjectIterator(JsonObject jObject, String key, String reObject) 
	{
		int beginIndex = 0;
		int endIndex = 0;
		//System.out.println(vArray);
		String objectTobeAdded = "";
		for (char val : reObject.toCharArray())
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
		JsonObject jinnerObject = new JsonObject();
		jObject.getMapObject().put(key, jinnerObject);
		String restObject = reObject.substring(objectTobeAdded.length());
		objectTobeAdded = objectTobeAdded.substring(1,objectTobeAdded.lastIndexOf('}'));
		char val[] = objectTobeAdded.toCharArray();
		for (int i=0 ; i<val.length;)
		{	
			switch(val[i])
			{
				case '{':

					String restJObject = objectTobeAdded.substring(i);
					String innerkey = objectTobeAdded.substring(0,objectTobeAdded.indexOf(restJObject));
					//System.out.println(restObject);
					innerkey = innerkey.substring(innerkey.lastIndexOf(',')+1);
					innerkey = innerkey.replaceAll("[//:]", "").replace("\"","").trim();
					String tempRemain = addMapJsonObjectIterator(jinnerObject,innerkey,restJObject);
					i = objectTobeAdded.length() - tempRemain.length();
					break;
				case '"':
					String restString = objectTobeAdded.substring(i);
					if(stringObject(restString))
					{
						if (restString.contains(","))
						{
							String mapString = restString.substring(0,restString.indexOf(','));
							String[] tempArray = mapString.split(":");
							String keyString = tempArray[0];
							keyString = keyString.replaceAll("[//:]", "").replace("\"","").trim();
							String valString = tempArray[1];
							valString = valString.replaceAll("[//:]", "").replace("\"","").trim();
							addMapStringToObject(jinnerObject, keyString, valString);
							i = i + mapString.length();
						}
						else if(restString.contains(":"))
						{
							String[] tempArray = restString.split(":");
							String keyString = tempArray[0];
							keyString = keyString.replaceAll("[//:]", "").replace("\"","").trim();
							String valString = tempArray[1];
							valString = valString.replaceAll("[//:]", "").replace("\"","").trim();
							addMapStringToObject(jinnerObject, keyString, valString);
							i = i + restString.length();
						}
					}
					break;
				case '}':
					break;
				case '[':
					String arrObject = objectTobeAdded.substring(i);
					String arrkey = objectTobeAdded.substring(0,objectTobeAdded.indexOf(arrObject));
					arrkey = arrkey.substring(arrkey.lastIndexOf(',')+1).trim();
					arrkey = arrkey.replaceAll("[//:]", "").replace("\"","").trim();
					String temparrRemain = addMapArrayToObject(jinnerObject,arrkey,arrObject);
					i = objectTobeAdded.length() - temparrRemain.length();
					break;
				case ']':
					break;
				case ':':
					break;
				
			}
			System.out.println(i);
			i++;
		}
		return restObject;
	}

	private static String addMapArray(Json json, String key, String vArray)
	{
		//parse string and assign to object here
		int beginIndex = 0;
		int endIndex = 0;
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> objectList = new ArrayList<String>();
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
		arrayTobeAdded = arrayTobeAdded.substring(1,arrayTobeAdded.lastIndexOf(']'));
		System.out.println(arrayTobeAdded);
		char[] arrIterator = arrayTobeAdded.toCharArray();
		//remove only array and object from string
		for (int i=0 ; i<arrIterator.length;)
		{
			switch(arrIterator[i])
			{
			case '{':
				String objectLiteral = getObjectIntheArray(arrayTobeAdded.substring(i));
				i = i + objectLiteral.length();
				addObjectToArray(jArray,new JsonObject(), objectLiteral);
				System.out.println(objectLiteral);
				objectList.add(objectLiteral);
				break;
			case '[':
				String arrayLiteral = getArrayIntheArray(arrayTobeAdded.substring(i));
				i = i +  arrayLiteral.length();
				System.out.println(arrayLiteral);
				addMapArrayIterator(jArray,arrayLiteral);
				arrayList.add(arrayLiteral);
				break;
			default:
				break;
				
			}
			i++;
		}
		
		for (String temp : objectList)
		{
			arrayTobeAdded = arrayTobeAdded.replace(temp, "");
		}
		
		for (String temp : arrayList)
		{
			arrayTobeAdded = arrayTobeAdded.replace(temp, "");
		}
		
		System.out.println(arrayTobeAdded);
		String[] values = arrayTobeAdded.split(",");
		for (String tempValues : values)
		{
			if(tempValues.contains("true"))
			{
				jArray.getBoolList().add(true);
			}
			else if (tempValues.contains("false"))
			{
				jArray.getBoolList().add(false);
			}
			else
			{
				try
				{
					Long var = Long.parseLong(tempValues);
					jArray.getLongList().add(var);
				}
				catch ( NumberFormatException ne)
				{
					Double dVar = Double.parseDouble(tempValues);
					jArray.getDoubleList().add(dVar);
				}
			}
		}
		return restObject;
	}
	
	private static boolean addMapArrayIterator(JsonArray jArray, String arrayLiteral)
	{
		boolean retVal = true;
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> objectList = new ArrayList<String>();
		JsonArray jinnerArray = new JsonArray();
		jArray.getArrayList().add(jinnerArray);
		arrayLiteral = arrayLiteral.substring(1,arrayLiteral.lastIndexOf(']'));
		System.out.println(arrayLiteral);
		char[] arrIterator = arrayLiteral.toCharArray();
		//remove only array and object from string
		for (int i=0 ; i<arrIterator.length;)
		{
			switch(arrIterator[i])
			{
			case '{':
				String objectLiteral = getObjectIntheArray(arrayLiteral.substring(i));
				i = i + objectLiteral.length();
				addObjectToArray(jinnerArray,new JsonObject(), objectLiteral);
				System.out.println(objectLiteral);
				objectList.add(objectLiteral);
				break;
			case '[':
				String insidearrayLiteral = getArrayIntheArray(arrayLiteral.substring(i));
				i = i +  insidearrayLiteral.length();
				System.out.println(insidearrayLiteral);
				addMapArrayIterator(jinnerArray,insidearrayLiteral); //do manipulation later
				arrayList.add(insidearrayLiteral);
				break;
			default:
				break;
				
			}
			i++;
		}
		
		for (String temp : objectList)
		{
			arrayLiteral = arrayLiteral.replace(temp, "");
		}
		
		for (String temp : arrayList)
		{
			arrayLiteral = arrayLiteral.replace(temp, "");
		}
		
		System.out.println(arrayLiteral);
		String[] values = arrayLiteral.split(",");
		for (String tempValues : values)
		{
			if(tempValues.contains("true") && !(tempValues.equals("")))
			{
				jinnerArray.getBoolList().add(true);
			}
			else if (tempValues.contains("false") && !(tempValues.equals("")))
			{
				jinnerArray.getBoolList().add(false);
			}
			else if (!(tempValues.equals("")))
			{
				try
				{
					Long var = Long.parseLong(tempValues);
					jinnerArray.getLongList().add(var);
				}
				catch ( NumberFormatException ne)
				{
					Double dVar = Double.parseDouble(tempValues);
					jinnerArray.getDoubleList().add(dVar);
				}
			}
		}
		
		return retVal;
	}
	
	private static String getObjectIntheArray(String substring) 
	{
		int startIndex = 0;
		int endIndex = 0;
		String objectToReturn = "";
		for (char temp : substring.toCharArray())
		{
			if(temp == '{')
				startIndex++;
			if(temp == '}')
				endIndex++;
			objectToReturn += temp;
			
			if(startIndex == endIndex)
				break;
			
		}
		return objectToReturn;
	}
	
	private static String getArrayIntheArray(String substring) 
	{
		int startIndex = 0;
		int endIndex = 0;
		String arrayToReturn = "";
		for (char temp : substring.toCharArray())
		{
			if(temp == '[')
				startIndex++;
			if(temp == ']')
				endIndex++;
			arrayToReturn += temp;
			
			if(startIndex == endIndex)
				break;
			
		}
		return arrayToReturn;
	}

	private static String addMapArrayToObject(JsonObject json, String key, String vArray)
	{
		//parse string and assign to object here
		int beginIndex = 0;
		int endIndex = 0;
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> objectList = new ArrayList<String>();
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
		arrayTobeAdded = arrayTobeAdded.substring(1,arrayTobeAdded.lastIndexOf(']'));
		JsonArray jArray = new JsonArray();
		json.getMapArray().put(key, jArray);
		char[] arrIterator = arrayTobeAdded.toCharArray();
		//remove only array and object from string
		for (int i=0 ; i<arrIterator.length;)
		{
			switch(arrIterator[i])
			{
			case '{':
				String objectLiteral = getObjectIntheArray(arrayTobeAdded.substring(i));
				i = i + objectLiteral.length();
				addObjectToArray(jArray,new JsonObject(), objectLiteral);
				System.out.println(objectLiteral);
				objectList.add(objectLiteral);
				break;
			case '[':
				String arrayLiteral = getArrayIntheArray(arrayTobeAdded.substring(i));
				i = i +  arrayLiteral.length();
				System.out.println(arrayLiteral);
				addMapArrayIterator(jArray,arrayLiteral);
				arrayList.add(arrayLiteral);
				break;
			default:
				break;
				
			}
			i++;
		}
		
		for (String temp : objectList)
		{
			arrayTobeAdded = arrayTobeAdded.replace(temp, "");
		}
		
		for (String temp : arrayList)
		{
			arrayTobeAdded = arrayTobeAdded.replace(temp, "");
		}
		
		System.out.println(arrayTobeAdded);
		String[] values = arrayTobeAdded.split(",");
		for (String tempValues : values)
		{
			if (!(tempValues.equals("")))
					{
						if(tempValues.contains("true"))
						{
							jArray.getBoolList().add(true);
						}
						else if (tempValues.contains("false"))
						{
							jArray.getBoolList().add(false);
						}
						else
						{
							try
							{
								Long var = Long.parseLong(tempValues);
								jArray.getLongList().add(var);
							}
							catch ( NumberFormatException ne)
							{
								Double dVar = Double.parseDouble(tempValues);
								jArray.getDoubleList().add(dVar);
							}
						}
					}
		}
		
		return restObject;
	}
	
	private static void addObjectToArray(JsonArray jArray, JsonObject jObject, String objectLiteral)
	{
		jArray.getObjectList().add(jObject);
		objectLiteral = objectLiteral.substring(1,objectLiteral.lastIndexOf('}'));
		char val[] = objectLiteral.toCharArray();
		JsonObject jinnerObject = new JsonObject();
		for (int i=0 ; i<val.length;)
		{	
			switch(val[i])
			{
				case '{':
					
					String restJObject = objectLiteral.substring(i);
					String innerkey = objectLiteral.substring(0,objectLiteral.indexOf(restJObject));
					//System.out.println(restObject);
					innerkey = innerkey.substring(innerkey.lastIndexOf(',')+1);
					innerkey = innerkey.replaceAll("[//:]", "").replace("\"","").trim();
					String tempRemain = addMapJsonObjectIterator(jinnerObject,innerkey,restJObject);
					i = objectLiteral.length() - tempRemain.length();
					break;
				case '"':
					String restString = objectLiteral.substring(i);
					if(stringObject(restString))
					{
						if (restString.contains(","))
						{
							String mapString = restString.substring(0,restString.indexOf(','));
							String[] tempArray = mapString.split(":");
							String keyString = tempArray[0];
							keyString = keyString.replaceAll("[//:]", "").replace("\"","").trim();
							String valString = tempArray[1];
							valString = valString.replaceAll("[//:]", "").replace("\"","").trim();
							addMapStringToObject(jObject, keyString, valString);
							i = i + mapString.length();
						}
						else if(restString.contains(":"))
						{
							String[] tempArray = restString.split(":");
							String keyString = tempArray[0];
							keyString = keyString.replaceAll("[//:]", "").replace("\"","").trim();
							String valString = tempArray[1];
							valString = valString.replaceAll("[//:]", "").replace("\"","").trim();
							addMapStringToObject(jObject, keyString, valString);
							i = i + restString.length();
						}
					}
					break;
				case '}':
					break;
				case '[':
					String arrObject = objectLiteral.substring(i);
					String arrkey = objectLiteral.substring(0,objectLiteral.indexOf(arrObject));
					arrkey = arrkey.substring(arrkey.lastIndexOf(',')+1).trim();
					arrkey = arrkey.replaceAll("[//:]", "").replace("\"","").trim();
					String temparrRemain = addMapArrayToObject(jinnerObject,arrkey,arrObject);
					i = objectLiteral.length() - temparrRemain.length();
					break;
				case ']':
					break;
				case ':':
					break;
				
			}
			System.out.println(i);
			i++;
		}
	}
	

}
