package model;

import java.util.List;

public class SearchWord {
	//フィールド
	private String mainId;
	private String subId;
	private String freeWord;
	
	//コンストラクタ
	public SearchWord() {}
	public SearchWord(String mainId,String subId,String freeWord) {
		this.mainId=mainId;
		this.subId=subId;
		this.freeWord=freeWord;
	}
	
	//メソッド
	public String getMainId() {
		String r=null;
		if(this.mainId!=null) {
			if(this.mainId.equals("A")) {
				r="犬";
			}else if(this.mainId.equals("B")){
				r="猫";
			}
		}
		
		return r;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public String getSubId() {
		String r=null;
		if(this.subId!=null) {
			switch(Integer.parseInt(this.subId)) {
			case 1:r="フード";
					break;
			case 2:r="ファッション";
				break;
			case 3:r="オモチャ";
				break;
			case 4:r="お散歩・お出かけ";
				break;
			case 5:r="ヘルス・ケア";
				break;
			case 6:r="ハウス";
				break;
			case 7:r="その他";
				break;
			}
		}
		
		return r;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String[] getFreeWord() {
		String[] r=null;
		if(this.freeWord!=null && !this.freeWord.isEmpty()) {
			String f=freeWord.replaceAll("　", " ");
			r=f.split(" ");
		}
		
		return r;
	}
	public void setFreeWord(String freeWord) {
		this.freeWord = freeWord;
	}
	
	public List<Item> findItem(){
		SearchItemLogic sil=new SearchItemLogic();
		
		List<Item> itemList=sil.searchItemLogic(this.mainId, this.subId, this.freeWord);
		
		return itemList;
	}
	
	
}
