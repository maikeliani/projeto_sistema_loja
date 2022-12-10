package br.com.seuzestore.system.communication;

import java.util.Scanner;

import br.com.seuzestore.system.data.SalesData;
import br.com.seuzestore.system.data.StockData;
import br.com.seuzestore.system.model.Sales;
import br.com.seuzestore.system.service.SalesService;
import br.com.seuzestore.system.service.StockService;

public class Menu {
	
	boolean continua;
	Integer choice;
	StockService service = new StockService();
	StockData data = new StockData();
	SalesService sale = new SalesService();
	SalesData salesData = new SalesData();
	
	Scanner sc = new Scanner(System.in);
	public void showMenu() {
		
		
	
		
		do {
			
			System.out.println("\n\n          <<<<<<<<<< INITIAL MENU >>>>>>>>>> \n");
			
			System.out.print("        SALES MENU------------------------------------------- ENTER '1' \n");
			System.out.print("        STOCK MENU------------------------------------------- ENTER '2' \n");			
			System.out.print("        BACK------------------------------------------------- ENTER '0' \n");
		
			
			
			
			choice = sc.nextInt();
			continua = true;
			
			
			
			switch(choice) {
				
					case 1: {				
						showSalesMenu();
						break;
					}
					
					case 2: {
						showStockMenu();
						break;
					}
					
					case 0: {
						continua = false;
						System.out.println("saindo...");
						sc.close();
						break;
					}
					
					default: {
						System.out.println("Opcao invalida. Escolha novamente.");
						break;
					}
				}
			
		  }while(continua);
	}
	
	public void showStockMenu() {
	
	
	do {
		
		System.out.println("\n\n                      <<<<<<<<<< STOCK MENU >>>>>>>>>> \n");
		
		System.out.print("        REGISTER PRODUCT------------------------------------- ENTER '1' \n");
		System.out.print("        DELETE PRODUCT--------------------------------------- ENTER '2' \n");
		System.out.print("        CONSULT STOCK---------------------------------------- ENTER '3' \n");		
		System.out.print("        SEARCH PRODUCT QUANTITY BY CODE---------------------- ENTER '4' \n");
		System.out.print("        BACK------------------------------------------------- ENTER '5' \n");
	
		
		
		
		choice = sc.nextInt();
		continua = true;
		
		
		
		switch(choice) {
		
			case 1: {
				
					System.out.println("Informe o SKU do produto: ");
					String sku = sc.next();
					
					System.out.println("Informe a quantidade do produto");
					int quantity = sc.nextInt();
					
					System.out.println("Informe o preço do produto por unidade");
					double price = sc.nextDouble();
					
					System.out.println("Informe a descricao do produto:");
					String description = sc.next();
					
					if(service.cadProd(sku, quantity, price, description)) {
						System.out.println("Produto cadastrado com sucesso ");
					}else {
						System.out.println("Erro, produto nao cadastrado! SKU incorreto");
					}
					break;
			}
			
			case 2: {
					System.out.println(service.consultStock());
					System.out.println("Informe o codigo do produto que deseja deletar: ");
					String cod = sc.next();
					System.out.println(service.deleteProduct(cod));
					break;
			}
			
			case 3: {
				
					System.out.println(service.consultStock());
					break;
			}
			
			case 4: {
					System.out.println("informe o codigo do produto: ");
					String code = sc.next();
					System.out.print("Quantidade em estoque: ");
					System.out.println(service.checkQuantityInStock(code));
					break;
			}
	
			case 5: {
					
					continua = false;					
					showMenu();				
					break;
			}
			
			default: {
				System.out.println("Opcao invalida. escolha novamente.");
				break;
			}
		}
	}while(continua);

}
	
	public void showSalesMenu() {
	
	
	do{
		
			System.out.println("\n\n                       <<<<<<<<<< SALES MENU >>>>>>>>>> \n");
			
			System.out.print("        ADD PRODUCT TO SHOPPING CART------------------------- ENTER '1' \n");
			System.out.print("        FINALIZE PURCHASE-------------------------------------ENTER '2' \n");
			System.out.print("        PURCHASE REGISTRATION---------------------------------ENTER '3' \n");			
			System.out.print("        DELETE PURCHASES FROM THE REGISTRATION--------------- ENTER '4' \n");
			System.out.print("        BACK------------------------------------------------- ENTER '5' \n");
		
			
			
			
			choice = sc.nextInt();
			continua = true;
			
		
		
			switch(choice) {
			
	
				case 1: {
					String produtoEscolhido;
					if(!service.StockEmpty()) {
						System.out.println(" Produtos disponiveis:\n ----------------");
						if(service.listProducts()) {
							System.out.println(" \n Digite o nome do produto escolhido: \n");
							 produtoEscolhido = sc.next();
						}else {
							break;
						}
							
						
						if(service.returnCodeByName(produtoEscolhido) == null) { 
							System.out.println("Produto informado nao foi localizado");
						}else {				
							
								String productCode = service.returnCodeByName(produtoEscolhido);	
								System.out.println("Informe a quantidade que deseja comprar: ");
								int quantity = sc.nextInt();								
								System.out.println(sale.addPurchaseToCart(productCode, quantity, service.searchPrice(productCode), produtoEscolhido));
								if(sale.shoppingCartContent().isEmpty()) {
									System.out.println(" ");
								}else {
									System.out.print(" conteudo do carrinho: " + sale.shoppingCartContent());
								}
								 						
							   }			
					}else {
						System.out.println("Nenhum produto cadastrado");
					}
					break;
				}
				
				case 2:{
					
					boolean check = true;	
					
					if(!sale.checkShoppingCart()) {
						System.out.println("Sem produtos no carrinho!");
					}else{					
						
						do {
							System.out.println("Deseja adicionar CPF na nota?   Digite  'Y' para registrar o cpf  ou digite 'N' para comprar sem cpf"); 
							String option = sc.next();
							
							if(option.equals("y") ||option.equals("Y") ) {
								System.out.println("Informe o CPF");
								String cpf = sc.next();
								if(sale.validateCpf(cpf)) {
									System.out.println("Você adicionou o cpf: " + cpf + " na nota da compra");
									System.out.println(sale.registerSale(sale.shoppingCartContent(), cpf)); 
									check = false;
								}else {
									System.out.println(" Cpf invalido. \n\n");
								}
								
							}else if(option.equals("n") ||option.equals("N") )  {
								System.out.println("Voce escolheu comprar sem CPF na nota"); 
								String cpf = "SEM CPF";
								System.out.println(sale.registerSale(sale.shoppingCartContent(), cpf));
								check = false;
							}
							
						} while(check);
						
						} 
					break;
				}	
				
				case 3: {
					sale.returnSales();			
					
					break;
				}
				
				case 4: {
					if(salesData.listItens().isEmpty()) {
						System.out.println(" Sem vendas registradas");
					}else {						 
							System.out.print("Informe o codigo da compra que deseja deletar:  >>>");
							String cod = sc.next();						
							if(salesData.delete(cod)) {
								System.out.println("Venda deletada");
							}else {
								System.out.println("Venda não encontrada");
									
								}
							}
						
					break;
				}
				
				case 5: {
						continua = false;						
						showMenu();
						break;						
						
				}		
				default: {
						System.out.println("OPCAO INCORRETA... DIGITE NOVAMENTE A OPCAO DESEJADA");			
						break;
				}
		
			}
		
		}while(continua);
	
	}

}	
		
		
	
	
	


