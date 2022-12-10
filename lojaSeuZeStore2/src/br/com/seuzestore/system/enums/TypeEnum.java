package br.com.seuzestore.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum TypeEnum {
	NACIONAL("N"),
	IMPORTADO("I");
	
	String cod;
	
	
	private final static Map<String, TypeEnum> getType = new HashMap<>();
	
	static {
			for(TypeEnum type : TypeEnum.values()) {
				getType.put(type.getCod(), type);
			}
	}
	
	TypeEnum(String cod) {
		this.cod = cod;
	}


	public String getCod() {
		return cod;
	}
	
	
	
	public static TypeEnum getTypeEnum(String cod) {
		return getType.get(cod);
	}
	
	
	
	
}
