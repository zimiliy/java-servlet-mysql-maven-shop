package model;

public class Item {
	//フィールド
	private String iID;
	private String name;
	private int price;
	private String image;
	private String information;
	private String spec;
	private String material;
	private int stock;
	private int buyCount;
	private int deleteCheck;
	private String tag;
	
	//コンストラクタ
	public Item() {}
	
	public Item(String iID, String name, int price,String image, String information, String spec,  String material, int stock, int buyCount,int deleteCheck, String tag) {
		this.iID = iID;
		this.name = name;
		this.price = price;
		this.image = image;
		this.information = information;
		this.spec = spec;
		this.material = material;
		this.stock = stock;
		this.buyCount = buyCount;
		this.deleteCheck=deleteCheck;
		this.tag = tag;
	}

	
	//IID
	public String getIID() {
		return iID;
	}
	public void setIID(String iID) {
		this.iID = iID;
	}
	
	//Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//price
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	//image
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	//information
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	
	//spec
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	//material
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	
	//stock
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	//bycount
	public int getBuyCount() {
		return buyCount;
	}
	public void setByCount(int byCount) {
		this.buyCount = byCount;
	}
	
	//deleteCheck
	public int getDeleteCheck() {
		return deleteCheck;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
}
