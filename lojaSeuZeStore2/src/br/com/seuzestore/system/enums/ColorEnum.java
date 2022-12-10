package br.com.seuzestore.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum ColorEnum {
	AZUL("AZ", "azul escuro"),
	BRANCO("BR", "branco ice"),
	AMARELO("AM", "amarelo claro"),
	PRETO("PT", "preto fosco"), 
	VERDE("VD", "verde claro");
	
	private String color;
	private String description;
	

	private final static Map<String, ColorEnum> map = new HashMap<>();
	
	static {
			for(ColorEnum color : ColorEnum.values()) {
				map.put(color.getColor(), color);
			}
	}

	ColorEnum(String color, String desc) {
		this.color = color;
		this.description = desc;
	}

	public String getColor() {
		return color;
	}

	public String getDescription() {
		return description;
	}
	
	public static  ColorEnum getColorEnum(String cod) {
		return map.get(cod);
	}
	
	
	
}
