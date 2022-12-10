package br.com.seuzestore.system.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import br.com.seuzestore.system.data.SalesData;
import br.com.seuzestore.system.data.StockData;
import br.com.seuzestore.system.enums.PaymentEnum;
import br.com.seuzestore.system.model.Product;
import br.com.seuzestore.system.model.Sales;

public class SalesService {
	Scanner sc = new Scanner(System.in);
	private  List<Product> shoppingCart = new ArrayList<>(); 
	StockService service = new StockService();
	SalesData data = new SalesData();
	Sales sale = new Sales();      
	StockData stock = new StockData();
	List<Object> sales = data.listItens();
	List<Object> stockItems = stock.listItens();
	Map<String,Integer> updatedValues = new HashMap<String,Integer>();	
	private static  String saleTextNumber = "SALE_NUMBER:";  
	private int saleNumber = 1;
	private String saleLabel = saleTextNumber + saleNumber;  
	
	
	

	public String addPurchaseToCart(String code, int number, double price, String description ) { 
		Product prod= new Product(code, number, price, description );	
		String codeProd = prod.getCode();
		int numberInStock = service.checkQuantityInStock(codeProd); 
		int sum=0;
		boolean added = false;
		String answer = " ";
		
		if(shoppingCart.isEmpty() && (prod.getQuantity() <= numberInStock)) {			
			shoppingCart.add(prod);			
			added = true; 
			
			
		}else {
			for(int x=0; x < shoppingCart.size(); x++) {
				Product product = shoppingCart.get(x);
				
				if(product.getCode().equals(codeProd)) { 
					sum += product.getQuantity();
					shoppingCart.remove(x);					
				}
			}
			
			prod.setQuantity(sum + prod.getQuantity());
			
			if(prod.getQuantity() <= numberInStock) {
				shoppingCart.add(prod);				
				added = true;
			}
			
			if(added) {
				answer = "Produto adicionado ao carrinho";
			}else {
				answer = "Produto indisponivel em estoque. No momento temos " + numberInStock + " itens disponiveis.";
						
			}
			
		}
		
			return answer;
	}	
						

	
	
	public Boolean emptyCart() {
		shoppingCart.removeAll(shoppingCart);
		return true;
	}
	
	
	
		

	public String registerSale(List<Product> cart, String cpf) {
		 List<Product> purchaseProducts = new ArrayList<>();	
		int numberInStock;
		if(shoppingCart.isEmpty()) {			
			return " Sem itens no carrinho de compras!";
		}
		
		Sales confirmedSale = new Sales(); 		
		boolean authorizedSale = false;
		int counter =0;	
		
		for(int x=0; x < cart.size(); x++) {
			Product product = cart.get(x);
			String code = product.getCode();
			numberInStock = service.checkQuantityInStock(code);
			if(numberInStock >= product.getQuantity()) {			
				counter++;
				updatedValues.put(code, (numberInStock - product.getQuantity()) ); 
			}else {
				System.out.println("quantidade selecionada: " + product.getQuantity()+ " estoque: " + service.checkQuantityInStock(code));
				System.out.println("Compra nao autorizada. Produto " + product.getDescription() + " quantidade insuficiente em estoque");
			}	
		}
		
		if(counter == cart.size()) {
			authorizedSale = true;
		}
		
		if(validateCpf(cpf)) {
			confirmedSale.setCpf(cpf);
		}else {
			confirmedSale.setCpf(" nao informado");
		}
		
		if(authorizedSale) { 	
			
			for(int x=0; x < cart.size(); x++) {
				Product prod = cart.get(x);
				purchaseProducts.add(prod);  
			}
			
			 
			
			confirmedSale.setDateTimePurchase(getDateTime()); 
			confirmedSale.setAmount(calcTotalValue());					
			confirmedSale.setProducts(purchaseProducts); 
			confirmedSale.setCode(saleLabel); 
			confirmedSale.setPayment(selectPaymentMethod());   
			if(confirmedSale.getPayment().getDescription().equals("PIX")) {
				System.out.println("Informe a chave PIX: ");
				String keyPix = sc.next();
				confirmedSale.setKeyPix(keyPix);
			}
			data.save(confirmedSale); 
			saleNumber++;
			
			
						//UPDATE STOCK AFTER SALE CONFIRMED!!
			for (Entry<String, Integer> map :  updatedValues.entrySet()) { 
	    		
		    	for( int i=0; i < stockItems.size(); i++) {
		    		Product product = (Product) stockItems.get(i);
		    		if(map.getKey().equals((product.getCode()))) { 
		    			product.setQuantity(map.getValue());
		    			stock.update(product);
		    		}
		    			
		    	}
		    }
			
			emptyCart();  
			System.out.println(" \n \n conteudo copia carrinho: " + purchaseProducts);
			 
			return "  \n Venda confirmada";
		}else {
			return " \n Erro, venda nao realizada";
		}		
	}

			
		public PaymentEnum selectPaymentMethod() {	
			int choose;
			System.out.println(" \n Informe a forma de pagamento: ");
			for(PaymentEnum payment : PaymentEnum.values()) {     
				System.out.print((payment.getDescription() + "- digite " + payment.getCode() + " / "));
			}
			
			 choose = sc.nextInt();
			 PaymentEnum chosenPayment = PaymentEnum.getMap(choose);
			 String payment = chosenPayment.getDescription();  
			System.out.println(" \n Voce escolheu: " + payment);
			
			

			return chosenPayment;
		}
			
			
	
	
	
	
	public boolean validateCpf(String cpf) {
		boolean value = cpf.matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
		
		if(value) {
			return true;
		}
		return false;
	}
	
	
	
	

	public BigDecimal calcTotalValue() {
		List<Product> list = shoppingCart;
		double totalValue = 0.00;
		for(int x=0; x < list.size(); x++) {
			 totalValue += list.get(x).getPrice() * list.get(x).getQuantity();
		}	
		BigDecimal formattedValue = new BigDecimal(totalValue).setScale(2, RoundingMode.HALF_EVEN);
		return formattedValue;		   
	}
	

	
	private String getDateTime() { 
		LocalTime now = LocalTime.now();
		LocalDate data = LocalDate.now();
		DateTimeFormatter mask1= DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter mask2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		String hour = mask2.format(now);
		String formattedDate = mask1.format(data);			
		return (formattedDate + " " + hour );
	}
	
	
	
	
	public String listSales() {
		
		String retorno = "Lista de Vendas: \n";		
		for ( int x =0; x < sales.size(); x++) {
			Sales sale = (Sales) sales.get(x);
			retorno += sale.toString();
		}
		return retorno;
	}
	
	
	
	public String deleteSale(long id) {
		if(data.delete(id)) {
			return "Venda deletada com sucesso!";
		}else {
			return " Venda  com codigo  + (" + id + ") nao encontrada";
		}
			
		
	}
	
	
	


	public List<Product> shoppingCartContent(){
		
		return shoppingCart;
	}

	
	
	public boolean checkShoppingCart() {
		if(!shoppingCart.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public String showShoppingList(){
		return data.listItens().toString();
	}
	

	
	public String returnPurchases() {
			
			if(sales.isEmpty()) { 
				 return "Nenhuma compra registrada.";
			}		
				String returnText =" LISTA DE VENDAS: \n";
				for( int x=0; x < sales.size(); x++) {
					Sales item = (Sales) sales.get(x);
					returnText+=  item.toString() + "\n";  
				}
				return returnText;
			}
	

	
	
	public void returnSales() {
		
		if(sales.isEmpty()) { 
			 System.out.println( "Nenhuma compra registrada.");
		}		
			
			for( int x=0; x < sales.size(); x++) {
				Sales item = (Sales) sales.get(x);
				System.out.println( item.ToString() );	 
			}
			
		}

	
}

	



