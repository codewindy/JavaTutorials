package com.codewindy.mongodb.pojo;

import java.util.HashMap;
import java.util.Map;

public class ShareContext {
	public Map<String,Object> share = new HashMap<String,Object>();
	
	public Object get(String key){
		return share.get(key);
	}
	
	public void set(String key,Object value){
		share.put(key, value);
	}
}
