package br.com.seuzestore.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum DepartmentEnum {
	MASCULINO("MAS"),
	FEMININO("FEM"),
	INFANTIL("INF"),
	INFANTOJUVENIL("JUV");
	
	private String cod;
	
	private final static Map<String, DepartmentEnum> map = new HashMap<>();
	
	static {
			for(DepartmentEnum department : DepartmentEnum.values()) {
				map.put(department.getCod(), department);
			}
	}

	
	
	public String getCod() {
		return cod;
	}


	DepartmentEnum(String cod) {
		this.cod = cod;
	}
	
	public static DepartmentEnum getDept(String cod) {
		return map.get(cod);
	}
	
	
}
