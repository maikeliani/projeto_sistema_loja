package br.com.seuzestore.system.model;

public class Person {

	private String name;
	private String cpf;
	private String address;
	
	
	
	
	public Person(String name, String cpf, String adress) {
		this.name = name;
		this.address = adress;
		this.cpf = cpf;
	}
	
	
	
	public String getNome() {
		return name;
	}
	
	public void setNome(String nome) {
		this.name = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEndereco() {
		return address;
	}
	
	public void setEndereco(String endereco) {
		this.address = endereco;
	}	
	
	
	
	
}

