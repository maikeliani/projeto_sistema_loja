package br.com.seuzestore.system.data;

import java.util.ArrayList;
import java.util.List;

import br.com.seuzestore.system.model.Sales;

public class SalesData implements DataInterface {
	
	
	
	private static List<Object> salesList = new ArrayList<>(); 
	

	@Override
	public void save(Object obj) {
		salesList.add(obj);
		
	}

	@Override
	public void update(Object obj) {
		Sales saleReceived  = (Sales) obj;
		for( int x=0; x < salesList.size(); x++) { 
			Sales sale = (Sales)salesList.get(x);
			if(sale.getCode() == saleReceived.getCode()) {
			salesList.set(x, saleReceived);
			break;
		
			}
		}
		
	}

	@Override
	public boolean delete(Object obj) {
		for(int x=0; x < salesList.size(); x++) {
			Sales sale = (Sales) salesList.get(x);
			if(sale.getCode().equals(obj)) { 
				salesList.remove(x);
				return true;
			}
		}
		return false;
	}

	
	@Override
	public Object getItem(String id) {
		
		for( int x = 0; x < salesList.size(); x++) {
			Sales sale = (Sales) salesList.get(x);
			if(sale.getCode().equals(id)) { 
				return sale;
				
			}
		}
		return null;
	}

	@Override
	public List<Object> listItens() {
		
		return salesList;
	}




	
	
}

