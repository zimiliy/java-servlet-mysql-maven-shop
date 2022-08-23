package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateItemListLogic {
	
	public List<Item> CreatItemList(ResultSet rs) throws SQLException{
		
		List<Item> itemList=new ArrayList<>();
		while(rs.next()) {
			String iID=rs.getString("IID");
			String name=rs.getString("NAME");
			int price=rs.getInt("PRICE");
			String image=rs.getString("IMAGE");
			String info=rs.getString("INFORMATION");
			String spec=rs.getString("SPEC");
			String material=rs.getString("MATERIAL");
			int stock=rs.getInt("STOCK");
			int buycount=rs.getInt("BUYCOUNT");
			int deleteCheck=rs.getInt("DELETECHECK");
			String tag=rs.getString("TAG");
			Item item = new Item(iID,name,price,image,info,spec,material,stock,buycount,deleteCheck,tag);
			itemList.add(item);
		}
		return itemList;
	}
	
	public List<Item> CreateFreeWordSearchList(ResultSet rs,List<Item> itemList) throws SQLException{
		while(rs.next()) {
			boolean check=true;
			String iID=rs.getString("IID");
			String name=rs.getString("NAME");
			int price=rs.getInt("PRICE");
			String image=rs.getString("IMAGE");
			String info=rs.getString("INFORMATION");
			String spec=rs.getString("SPEC");
			String material=rs.getString("MATERIAL");
			int stock=rs.getInt("STOCK");
			int buycount=rs.getInt("BUYCOUNT");
			int deleteCheck=rs.getInt("DELETECHECK");
			String tag=rs.getString("TAG");
			for(Item item : itemList) {
				if(item.getIID().equals(iID)) {
					check=false;
				}
			}
			if(check) {
				Item item=new Item(iID,name,price,image,info,spec,material,stock,buycount,deleteCheck,tag);
				itemList.add(item);
			}
			check=true;
		}
		return itemList;

	}

}
