package br.com.seuzestore.system.model;




import br.com.seuzestore.system.enums.CategoryEnum;
import br.com.seuzestore.system.enums.ColorEnum;
import br.com.seuzestore.system.enums.DepartmentEnum;
import br.com.seuzestore.system.enums.SizeEnum;
import br.com.seuzestore.system.enums.TypeEnum;

public class Product {
	
	private String name;	
 	private ColorEnum color;
	private TypeEnum type;
	private SizeEnum size;
	private double price;
	private String description;
	private DepartmentEnum department;
	private int quantity;   
	private CategoryEnum category;
	private String sku; 
	
	
	


	public Product(String code, int productQuantity, double value, String description) {
		this.sku = code;
		this.quantity = productQuantity;
		this.price = value;
		this.description = description;
		parseSku();  
	
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return " \n\n Produto: "
				+ "  \n Codigo:  " + this.sku 
				+ "	 \n Nome: "+ this.description 
				+ "  \n Tipo: " + this.type  
				+ "  \n Categoria: " +this.category
				+ "  \n Tamanho: " + this.size
				+ "  \n Cor: " + this.color
				+ "  \n Quantidade: " + this.quantity 
				+ "  \n Preco por unidade: " + this.price
				+ "  \n Departamento: " + this.department;
		
		
	}
	
	
	
	
	
	
	public DepartmentEnum getDepartment() {
		return department;
	}


	public void setDepartment(DepartmentEnum department) {
		this.department = department;
	}



	public CategoryEnum getCategory() {
		return category;
	}



	public void setCategory(CategoryEnum category) {
		this.category = category;
	}




	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public ColorEnum getColor() {
		return color;
	}



	public void setColor(ColorEnum color) {
		this.color = color;
	}



	public TypeEnum getType() {
		return type;
	}


	public void setType(TypeEnum type) {
		this.type = type;
	}


	public SizeEnum getSize() {
		return size;
	}


	public void setSize(SizeEnum size) {
		this.size = size;
	}




	public double getPrice() {
		return price;
	}



	public void setPrice(double value) {
		this.price = value;
	}




	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public int getQuantity() {
		return this.quantity;
	}


	public void setQuantity(int productQuantity) {
		this.quantity = productQuantity;
	}


	public String getCode() {
		return sku;
	}

	public void setCode(String code) {
		this.sku = code;
	}


	
	
	public void parseSku() { 
		String skuSize = sku.substring(0,1);   
		String skuType =  sku.substring(1,2);
		String skuDepartment =  sku.substring(2,5);
		String skuCategory =  sku.substring(5,9);
		String skuColor =  sku.substring(9);
		this.size = SizeEnum.getSizeEnum(skuSize);
		this.type = TypeEnum.getTypeEnum(skuType);
		this.department = DepartmentEnum.getDept(skuDepartment);
		this.category = CategoryEnum.getCategoryEnum(skuCategory);
		this.color = ColorEnum.getColorEnum(skuColor);	
	}
	
	
	
	
	

}

