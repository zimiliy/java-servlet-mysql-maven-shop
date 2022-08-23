package model;

import java.util.List;

import DAO.ItemDAO;

public class NewItemSearchLogic {
	public List<Item> NewItemSearch(){
		ItemDAO dao=new ItemDAO();
		List<Item> itemList=dao.findAllItem();
		
		ItemSortLogic isl=new ItemSortLogic();
		
		isl.sortByDate(itemList);
		
		return itemList;
	}
}
