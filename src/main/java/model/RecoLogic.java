package model;

import java.util.ArrayList;
import java.util.List;

import DAO.HistoryDAO;
import DAO.ItemDAO;

public class RecoLogic {
	//おすすめ
	public List<Item> reco(userDB userDB){
		List<Item> recoList=new ArrayList<>();

		HistoryDAO hDAO=new HistoryDAO();
		ItemDAO iDAO=new ItemDAO();
		List<CartItem> historyList=hDAO.findHistory(userDB);
		
		if(historyList.size()!=0) {
			String iId=historyList.get(historyList.size()-1).getItem().getIID();
			
			String[] split=iId.split("_");
			
			recoList=iDAO.findItem(split[0], split[2]); 
		}
		return recoList;

		
		
	}

}
