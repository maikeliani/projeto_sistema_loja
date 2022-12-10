package br.com.seuzestore.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentEnum {
	PIX("PIX",1),
	DEBIT("DEBITO",2),
	CREDIT("CREDITO",3),
	BANK_PAYMENT_SLIP("BOLETO A VISTA",4),
	INSTALLMENT_BANK_PAYMENT_SLIP("BOLETO PARCELADO" ,5),
	CASH("DINHEIRO A VISTA",6);
	
	private int code;
	private String description;
	private String keyPix;
	
	private final static Map<Integer, PaymentEnum> map = new HashMap<>();
	
	static {
		for(PaymentEnum payment : PaymentEnum.values()) {
			map.put(payment.getCode(), payment);
		}
	}
	
	
	
	PaymentEnum(String description, int code) {
		this.code = code;
		this.description = description;
	}
	

	public int getCode() {
		return code;
	}
	
	
	
	public String getDescription() {
		return description;
	}

	
	

	public String getKeyPix() {
		return keyPix;
	}


	public void setKeyPix(String keyPix) {
		this.keyPix = keyPix;
	}


	public static PaymentEnum getMap(int code) {
		return map.get(code);
	}


}
