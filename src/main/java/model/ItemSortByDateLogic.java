package model;

import java.util.Collections;
import java.util.List;

import DAO.ItemDAO;

public class ItemSortByDateLogic {
	//新着順
	public List<Item> sortByDate(){
		ItemDAO dao=new ItemDAO();
		List<Item> itemList=dao.findAllItem();
		
		Collections.sort(itemList, new NewItemSort());
		
		return itemList;
	}
}
