package com.codewindy.mongodb.pojo;

import java.io.Serializable;
import java.util.HashMap;

public class ServiceObject extends HashMap<String, Object> implements Serializable {
	private static final long serialVersionUID = -2181332443147593177L;

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		Object o = super.get(key);
		if (o != null) {
			return (T) o;
		}
		return null;
	}

	public boolean getBoolean(String key) {
		Object o = this.<Boolean>get(key);
		if (o != null && o instanceof Boolean) {
			return Boolean.parseBoolean(o.toString());
		}
		if (o != null && o instanceof String) {
			if ("true".equals(o)) {
				return true;
			}
		}
		return false;
	}
}