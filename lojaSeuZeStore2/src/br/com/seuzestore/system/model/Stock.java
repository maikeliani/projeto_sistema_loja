package br.com.seuzestore.system.model;

import java.util.List;

public class Stock {
	
	private List<Product> itens;
	

	public List<Product> getItens() {
		return itens;
	}

	public void setItens(List<Product> itens) {
		this.itens = itens;
	}
}
