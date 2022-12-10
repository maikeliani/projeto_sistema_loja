package br.com.seuzestore.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.seuzestore.system.data.StockData;
import br.com.seuzestore.system.enums.DepartmentEnum;
import br.com.seuzestore.system.model.Product;

public class StockService {

	StockData data = new StockData();
	Scanner sc = new Scanner(System.in);
	
	
	public boolean cadProd(String sku, int productQuantity, double price, String description) {		
		Product productReceived = new Product(sku, productQuantity, price, description); 
		List<Object> stock = data.listItens(); 
		boolean isNew =true;
		System.out.println("departamento: " +productReceived.getDepartment()); 
		System.out.println("tamanho: " + productReceived.getSize());
		System.out.println(" cor: " +productReceived.getColor());
		System.out.println( " categoria: " +productReceived.getCategory());
		System.out.println(" tipo: " +productReceived.getType());
		
		
		
		if(productReceived.getCategory() == null  ||
				productReceived.getDepartment() == null ||
				productReceived.getSize() == null ||
				productReceived.getColor() == null ||
				productReceived.getType() == null ) {
			return false;
		}
		
		
		for( int x=0; x < stock.size(); x++) {
			Product productStock = (Product)stock.get(x);
			if(productStock.getCode().equals(productReceived.getCode())) { 
				productStock.setDescription(productReceived.getDescription());
				productStock.setPrice(productReceived.getPrice()); 
				productStock.setQuantity(productStock.getQuantity() + productReceived.getQuantity()); 
				data.update(productStock);
				isNew = false;
			}			
		}
		
		if(isNew) {
			data.save(productReceived);
		}
		return true;
	} 


	
	
	public boolean listProducts() {
		
		String nameDept = null;
		  List<DepartmentEnum> dept = new ArrayList<>();
		if(data.listItens().isEmpty()) { 
			  System.out.println("Nenhum produto cadastrado");
		} else {
				do {
					int number =1;
					System.out.println("Informe o departamento para buscar o produto: \n");
					for(DepartmentEnum department : DepartmentEnum.values()) {
						System.out.print((department + "- digite " + number + " / "));
						dept.add(department); 
						number++;
						}
					System.out.print("SAIR - digite 0");
					int chosenNumber = sc.nextInt();
					if(chosenNumber == 0) {
						return false;
					}
					nameDept = dept.get(chosenNumber - 1).getCod(); 
				}while(returnProductsByDepartment(nameDept));
				
			}	
		return true;
		}
		
	
	
	public boolean returnProductsByDepartment(String codeDept) {
		int quantityProducts = 0;
		List<Object> stock = data.listItens();
		for( int x = 0; x < stock.size(); x++) {
			Product product = (Product) stock.get(x);
			if(product.getDepartment().getCod().equals(codeDept)) {
				System.out.println(product.toString());
				quantityProducts++;
				return false;
			}
		}
		if(quantityProducts == 0) {
			System.out.println("Sem produtos cadastrados nessa categoria \n\n");
		}
		return true;
	}
	

	
	
	public String showProducts() {
		
		if(data.listItens().isEmpty()) { 
			 return "Nenhum produto cadastrado.";
		} else {
			String retorno = "Lista de Produtos:\n \n";			
			List<Object> stock = data.listItens();
			for( int x = 0; x < stock.size(); x++) {
				Product product = (Product) stock.get(x);
				retorno += "-- " +product.getDescription() + "\n";
			}			
			return retorno;
		}
		
	}

	
	

	public String deleteProduct(String cod) {
		if(data.delete(cod)) {
			return "produto removido";
		}else {
			return "produto codigo (" + cod + ") nao encontrado";
		}
		
	}
	
	
	
	public int checkQuantityInStock(String id) { 
		List<Object> stock = data.listItens(); 
		int quantity = 0;
			for(int x=0; x < stock.size(); x++) {
				Product productStock = (Product)stock.get(x);
				if(productStock.getCode().equals(id)) { 
					quantity += productStock.getQuantity();	
					
				}
			}
			return quantity; 
		}




	public String consultStock() { 
		List<Object> stock = data.listItens(); 
		String values = "Produtos em estoque  \n\n";
			for(int x=0; x < stock.size(); x++) {
				Product productStock = (Product)stock.get(x);			
					values += productStock.toString() + "\n";				
				}
			
			return values; 
		}





	public String returnCodeByName(String name) {
		Product product = null;
		List<Object> stock = data.listItens();
		for(int x=0; x < stock.size(); x++) {
			 product = (Product) stock.get(x);
			if(product.getDescription().equals(name)) {			
				return product.getCode();
				
			}
		}
		return null;	
	}




	public boolean StockEmpty() {
		if(data.listItens().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	


	public double searchPrice(String code) {
		Product product;
		List<Object> stock = data.listItens();
		for(int x=0; x < stock.size(); x++) {
			 product = (Product) stock.get(x);
			if(product.getCode().equals(code)) {			
				return product.getPrice();
				
			}
		}
		return 0;
	}
	
}

