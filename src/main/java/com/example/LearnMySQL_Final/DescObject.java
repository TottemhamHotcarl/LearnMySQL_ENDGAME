package com.example.LearnMySQL_Final;

public class  DescObject {

	
	String Field;
	 String type;
	  String nulla;
	 String key;
	 String extra;
	 String defaulta;
	
	
	
	
	public DescObject(String field,String type,String nulla,String key,String defaulta, String extra) {
		this.Field = field;
		this.type = type;
		this.nulla = nulla;
		this.key = key;
		this.extra = extra;
		this.defaulta = defaulta;
	}
	
	
	public DescObject() {}
	
	
	public String getField() {
		return Field;
	}
	public String gettype() {
		return type; 
	}
	
	public String getnull() {
		return nulla;
	}
	
	public String getkey() {
		return key;
	}
	public String getextra() {
		return extra;
	}
	
	public String getdefault() {
		return defaulta;
	}
	
	
	
	
	
	
	
	
}
