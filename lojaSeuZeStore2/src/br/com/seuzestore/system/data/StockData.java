package br.com.seuzestore.system.data;

import java.util.ArrayList;
import java.util.List;

import br.com.seuzestore.system.model.Product;

public class StockData implements DataInterface {
	
	static Integer id =0;	
	

	

	 private  static List<Object> stock = new ArrayList<>();





	@Override
	public List<Object> listItens() {
		
		return stock;
	}

	@Override
	public void save(Object obj) {
		stock.add(obj);
	}
	

	@Override
	public void update(Object obj) {  
		Product productReceived  = (Product) obj;
		for( int x=0; x < stock.size(); x++) { 
			Product productStock = (Product)stock.get(x);
			if(productStock.getCode().equals(productReceived.getCode())) { 
			stock.set(x, productReceived);
			break;
		
			}
		}
	}
			

	@Override
	public boolean delete(Object obj) {
		String cod = (String) obj;
		for(int x=0; x < stock.size(); x++) {
			Product productList = (Product)stock.get(x);
			if(cod.equals(productList.getCode())) { 
				stock.remove(x);
				return true;
			}
				
		}
		return false;
	}

	@Override
	public Object getItem(String id) { 
		
		for(int x=0; x < stock.size(); x++) {
			Product productStock = (Product)stock.get(x);
			if(productStock.getCode().equals(id)) { 
				return productStock;
				
			}
		}
		return null;
	}



}



	
	



