package model;

import java.util.ArrayList;
import java.util.List;

import DAO.ItemDAO;

public class SearchItemLogic {
	public List<Item> searchItemLogic(String mainId,String subId,String freeWord){
		List<Item> itemList=new ArrayList<>();
		ItemDAO dao=new ItemDAO();
		if(freeWord==null) {
			itemList=dao.findItem(mainId, subId);
		}else{
			if(mainId==null) {
				itemList=dao.findItemFreeWord(freeWord);
			}else {
				itemList=dao.findItemFreeWordAndMainId(freeWord, mainId);
			}
		}
		
		return itemList;
	}
}
