package br.com.seuzestore.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum CategoryEnum {
	CALCADOS("CALC"),
	PERFUMES("PERF"),
	VESTUARIO("VEST"),
	ELETRONICO("ELET");
	
	
	
	private String ref;
	
	
private final static Map<String, CategoryEnum> getCategory = new HashMap<>();
	
	static {
			for(CategoryEnum category : CategoryEnum.values()) {
				getCategory.put(category.getRef(), category);
			}
	}

	CategoryEnum(String c) {
		this.ref = c;
	}
	
	public String getRef() {
		return ref;
	}
	
	public static CategoryEnum getCategoryEnum(String ref) {
		return getCategory.get(ref);
	}
	
}
