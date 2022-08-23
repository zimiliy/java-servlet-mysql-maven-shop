package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemSortLogic {
	//新着順
	public List<Item> sortByDate(List<Item> itemList){
		Collections.sort(itemList, new NewItemSort());
		
		return itemList;
	}
	
	//売上順
	public List<Item> sortByBuyCount(List<Item> itemList){
		Collections.sort(itemList, new BuyCountSort());
		
		return itemList;
	}
	
	//価格の高い順
	public List<Item> sortByHighPrice(List<Item> itemList){
		Collections.sort(itemList, new HighPriceSort());
		
		return itemList;
	} 
	
	//価格の安い順
	public List<Item> sortByLowPrice(List<Item> itemList){
		Collections.sort(itemList, new LowPriceSort());
		
		List<Item> itemList2=new ArrayList<>();
		
		for(int i=0;i<itemList.size();i++) {
			Item item=itemList.get(i);
			itemList2.add(item);
		}
		
		return itemList2;
	} 
}
