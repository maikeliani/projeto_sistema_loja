package br.com.seuzestore.system.data;

import java.util.List;

public interface DataInterface {
	
	void save(Object obj);
	
	void update(Object obj);
	
	boolean delete(Object obj);
	
	List<Object>listItens();
	
	Object getItem(String id); 
	
	

}
