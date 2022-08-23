package model;

public class CartItem {
	//フィールド
	private Item item;
	private int buyNum;
	
	//コンストラクタ
	public CartItem(Item item,int buyNum) {
		this.item=item;
		this.buyNum=buyNum;
	}
	
	//メソッド
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	
	}
}
