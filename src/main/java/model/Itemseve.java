package model;

import javax.servlet.http.Part;

public class Itemseve {
	private String mainid;
	private String subid;
	private String name;
	private int price;
	private Part image;
	private String information;
	private String spec;
	private String material;
	private int stock;
	
public Itemseve() {}
	
	public Itemseve(String mainid, String subid, String name, int price,Part image, String information, String spec,  String material, int stock) {
		this.mainid = mainid;
		this.subid = subid;
		this.name = name;
		this.price = price;
		this.image = image;
		this.information = information;
		this.spec = spec;
		this.material = material;
		this.stock = stock;

	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public String getSubid() {
		return subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
