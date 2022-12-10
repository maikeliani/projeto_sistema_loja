package br.com.seuzestore.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum SizeEnum {
	P("P"), M("M"), G("G"), GG("L"), XGG("X");
	
	private String cod;
	
	
private final static Map<String, SizeEnum> getSize = new HashMap<>();
	
	static {
			for(SizeEnum size : SizeEnum.values()) {
				getSize.put(size.getCod(), size);
			}
	}
	
	
	
	SizeEnum(String c) {
		this.cod = c;
	}



	public String getCod() {
		return cod;
	}



	public static SizeEnum  getSizeEnum(String cod) {
		return getSize.get(cod);
	}
		
	
}
