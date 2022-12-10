package br.com.seuzestore.system.model;

import java.math.BigDecimal;
import java.util.List;

import br.com.seuzestore.system.enums.PaymentEnum;

public class Sales {
	
	private List<Product>products; 
	private Double purchaseValue;
	private String cpf;
	private String dateTimePurchase;   
	private  String code;  
	private BigDecimal amount; 
	private PaymentEnum payment;  
	private String paymentDescription;
	

	
	
	public void setKeyPix(String key) {
		this.payment.setKeyPix(key);
	}
	
	public String getKeyPix() {
		return this.payment.getKeyPix();
	}


	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	public PaymentEnum getPayment() { 
		return payment;
	}

	public void setPayment(PaymentEnum payment) { 
		this.payment = payment;
	}

	public String getDateTimePurchase() { 
		return dateTimePurchase;
	}

	public void setDateTimePurchase(String dateTimePurchase) {
		this.dateTimePurchase = dateTimePurchase;
	}

	public Sales(String code, int productQuantity, double price, String description) {
		// TODO Auto-generated constructor stub
	}

	public Sales() {
	
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	

	public void setCode(String code) {
		this.code = code;
	}


	
	public List<Product> getProducts() {
		return products;
	}
	
	

	public void setProducts(List<Product> returnedProduct) {
		this.products = returnedProduct;
	}
		

	public Double getPurchaseValue() {
		
		return purchaseValue;
	}
	public void setPurchaseValue(Double purchaseValue) {
		this.purchaseValue = purchaseValue;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	public String ToString() {
		String text = "Venda codigo: " + this.code + "\n"
				+ "CPF: "  + this.cpf + "\n"
				+ "VALOR DA COMPRA: R$ " + this.amount + "\n"
				+ "DATA / HORA DA COMPRA: " + this.getDateTimePurchase() + "\n"
				+ "FORMA DE PAGAMENTO: " + this.payment.getDescription() + "\n";
				 
		
		if(this.payment.getKeyPix() != null) {
			text += "CHAVE PIX: " + this.payment.getKeyPix() + "\n";
		}
		text += "PRODUTOS  DA COMPRA: " + products.toString() + "\n";
		return text;
		
	}

	
}

