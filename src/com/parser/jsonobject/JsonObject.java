package com.parser.jsonobject;

public class JsonObject {
	
	private JsonKey key;
	private JsonValue value;
	
	public JsonKey getKey() {
		return key;
	}
	public void setKey(JsonKey key) {
		this.key = key;
	}
	public JsonValue getValue() {
		return value;
	}
	public void setValue(JsonValue value) {
		this.value = value;
	}
	
	public JsonObject()
	{
		setKey(new JsonKey());
		setValue(new JsonValue());
	}

}
