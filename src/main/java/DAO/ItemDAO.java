package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import model.CartItem;
import model.CreateItemListLogic;
import model.Item;

public class ItemDAO {
	//フィールド
	private final String JDBC_URL ="jdbc:mysql://localhost:3306/n_g?characterEncoding=UTF-8&serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "";
	
	//メソッド
	//カテゴリー検索
	public List<Item> findItem(String main,String sub){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		//検索
		List<Item> itemList=new ArrayList<>();
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			if(sub!=null) {
				String sql="SELECT * FROM ITEM WHERE DELETECHECK=0 AND IID LIKE ? AND IID LIKE ?";
				PreparedStatement pStmt=conn.prepareStatement(sql);
				
				main=main+"%";
				sub="%"+sub;
				pStmt.setString(1, main);
				pStmt.setString(2, sub);
				
				ResultSet rs=pStmt.executeQuery();
				
				//リスト作成
				CreateItemListLogic cill=new CreateItemListLogic();
				itemList=cill.CreatItemList(rs);
			}else {
				String sql="SELECT * FROM ITEM WHERE DELETECHECK=0 AND IID LIKE ?";
				PreparedStatement pStmt=conn.prepareStatement(sql);
				
				main=main+"%";
				pStmt.setString(1, main);
				
				ResultSet rs=pStmt.executeQuery();
				
				//リスト作成
				CreateItemListLogic cill=new CreateItemListLogic();
				itemList=cill.CreatItemList(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return itemList;

	}
	
	//商品追加
	public boolean addItem(String main,String sub,String name,int price,String image,String information,String spec,String material,int stock,String tag) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			Instant i=Instant.now();
			long l=i.toEpochMilli();
			
			String iId=main+"_"+l+"_"+sub;
			
			String sql="INSERT INTO Item(IID,NAME,PRICE,IMAGE,INFORMATION,SPEC,MATERIAL,STOCK,BUYCOUNT,TAG) VALUE(?,?,?,?,?,?,?,?,0,?)";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setString(1, iId);
			pStmt.setString(2, name);
			pStmt.setInt(3, price);
			pStmt.setString(4, image);
			pStmt.setString(5, information);
			pStmt.setString(6, spec);
			pStmt.setString(7, material);
			pStmt.setInt(8, stock);
			pStmt.setString(9, tag);
			
			int rs=pStmt.executeUpdate();
			if(rs!=1) {
				return false;
			}

			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	//フリーワード検索
	public List<Item> findItemFreeWord(String word){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		//キーワードリスト作成
		word=word.replaceAll("　", " ");
		String[] split=word.split(" ");
		
		for(int i=0;i<split.length;i++) {
			split[i]="%"+split[i]+"%";
		}
		
		List<Item> itemList=new ArrayList<>();
		
		if(word.equals("") || word.matches("\s{1,}")) {
			return itemList;
		}
		
		CreateItemListLogic cill=new CreateItemListLogic();
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//NAMEを検索
			//SQL文を作成
			String sql1="SELECT * FROM ITEM WHERE DELETECHECK=0 AND";
			for(int i=0;i<split.length;i++) {
				if(i!=0) {
					sql1=sql1+" OR";
				}
				sql1=sql1+" NAME LIKE ?";
			}
			
			PreparedStatement pStmt1=conn.prepareStatement(sql1);
			
			for(int i=0;i<split.length;i++) {
				pStmt1.setString(i+1, split[i]);
			}
			
			ResultSet rs1=pStmt1.executeQuery();
			
			//リスト作成			
			itemList=cill.CreatItemList(rs1);
			
			//INFORMATIONを検索
			//SQL文を作成
			String sql2="SELECT * FROM ITEM WHERE DELETECHECK=0 AND";
			for(int i=0;i<split.length;i++) {
				if(i!=0) {
					sql2=sql2+" OR";
				}
				sql2=sql2+" INFORMATION LIKE ?";
			}
			
			PreparedStatement pStmt2=conn.prepareStatement(sql2);
			
			for(int i=0;i<split.length;i++) {
				pStmt2.setString(i+1, split[i]);
			}
			
			ResultSet rs2=pStmt2.executeQuery();
			
			//リスト作成
			cill.CreateFreeWordSearchList(rs2, itemList);
			
			//SPECを検索
			//SQL文を作成
			String sql3="SELECT * FROM ITEM WHERE DELETECHECK=0 AND";
			for(int i=0;i<split.length;i++) {
				if(i!=0) {
					sql3=sql3+" OR";
				}
				sql3=sql3+" SPEC LIKE ?";
			}
			
			PreparedStatement pStmt3=conn.prepareStatement(sql3);
			
			for(int i=0;i<split.length;i++) {
				pStmt3.setString(i+1, split[i]);
			}
			
			ResultSet rs3=pStmt3.executeQuery();
			
			//リスト作成
			cill.CreateFreeWordSearchList(rs3, itemList);
			
			//MATERIALを検索
			//SQL文を作成
			String sql4="SELECT * FROM ITEM WHERE DELETECHECK=0 AND";
			for(int i=0;i<split.length;i++) {
				if(i!=0) {
					sql4=sql4+" OR";
				}
				sql4=sql4+" MATERIAL LIKE ?";
			}
			
			PreparedStatement pStmt4=conn.prepareStatement(sql4);
			
			for(int i=0;i<split.length;i++) {
				pStmt4.setString(i+1, split[i]);
			}
			
			ResultSet rs4=pStmt4.executeQuery();
			
			//リスト作成
			cill.CreateFreeWordSearchList(rs4, itemList);
			
			//TAGを検索
			//SQL文を作成
			String sql5="SELECT * FROM ITEM WHERE DELETECHECK=0 AND";
			for(int i=0;i<split.length;i++) {
				if(i!=0) {
					sql5=sql5+" OR";
				}
				sql5=sql5+" TAG LIKE ?";
			}
			
			PreparedStatement pStmt5=conn.prepareStatement(sql5);
			
			for(int i=0;i<split.length;i++) {
				pStmt5.setString(i+1, split[i]);
			}
			
			ResultSet rs5=pStmt5.executeQuery();
			
			//リスト作成
			cill.CreateFreeWordSearchList(rs5, itemList);

			
			List<String> removeList=new ArrayList<>();
			for(int in=0;in<itemList.size();in++) {
				String str="";
				int count=0;
				Item i=itemList.get(in);
				str=i.getName()+i.getInformation()+i.getMaterial()+i.getSpec()+i.getTag();
				for(String s:split) {
					s=s.replaceAll("%", "");
					if(str.contains(s)) {
						count++;
					}
				}
				if(count!=split.length) {
					removeList.add(i.getIID());
				}
			}
			for(String s:removeList) {
				int a=-1;
				for(Item i:itemList) {
					if(s.equals(i.getIID())) {
						a=itemList.indexOf(i);
					}
				}
				if(a!=-1) {
					itemList.remove(a);
				}
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return itemList;

	}
	
	//商品削除
	public boolean itemDelete(String iID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}

		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="UPDATE ITEM SET DELETECHECK=1 WHERE IID=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setString(1, iID);
			
			int rs=pStmt.executeUpdate();
			
			if(rs!=1) {
				return false;
			}
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	//商品編集
	public boolean editItem(String name,int price,String image,String information,String spec,String material,int stock,String iId,String tag) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="UPDATE ITEM SET NAME=?,PRICE=?,IMAGE=?,INFORMATION=?,SPEC=?,MATERIAL=?,STOCK=?,TAG=? WHERE IID=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setString(1, name);
			pStmt.setInt(2, price);
			pStmt.setString(3, image);
			pStmt.setString(4, information);
			pStmt.setString(5, spec);
			pStmt.setString(6, material);
			pStmt.setInt(7, stock);
			pStmt.setString(8, tag);
			pStmt.setString(9, iId);
			
			int rs=pStmt.executeUpdate();
			if(rs!=1) {
				return false;
			}

			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	//画像の名前を探す
	public List<String> findImageName(String imageName){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		//検索
		List<String> imageList=new ArrayList<>();
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql1="SELECT * FROM ITEM WHERE IMAGE LIKE ?";
			PreparedStatement pStmt=conn.prepareStatement(sql1);
			
			imageName=imageName+"%";
			pStmt.setString(1, imageName);
			
			ResultSet rs=pStmt.executeQuery();
			
			//リスト作成
			while(rs.next()) {
				String image=rs.getString("IMAGE");
				imageList.add(image);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return imageList;

	}
	
	
	//商品個別検索
	public Item findOneItem(String iId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}

		List<Item> itemList=new ArrayList<>();
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="SELECT * FROM ITEM WHERE IID=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setString(1, iId);
			
			ResultSet rs=pStmt.executeQuery();
			
			//リスト作成
			CreateItemListLogic cill=new CreateItemListLogic();
			itemList=cill.CreatItemList(rs);
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		Item item=itemList.get(0);
		return item;

	}
	
	//商品購入
	public boolean buyItem(List<CartItem> cartList) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			for(CartItem ci:cartList) {
				String sql="UPDATE ITEM SET STOCK=STOCK-?,BUYCOUNT=BUYCOUNT+? WHERE IID=?";
				PreparedStatement pStmt=conn.prepareStatement(sql);
				
				pStmt.setInt(1, ci.getBuyNum());
				pStmt.setInt(2, ci.getBuyNum());
				pStmt.setString(3, ci.getItem().getIID());
				
				int rs=pStmt.executeUpdate();
				if(rs!=1) {
					return false;
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
	
	//ランキング
	public List<Item> ranking(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		List<Item> itemList=new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql1="SELECT * FROM ITEM WHERE DELETECHECK=0 AND BUYCOUNT>0 ORDER BY BUYCOUNT DESC";
			PreparedStatement pStmt=conn.prepareStatement(sql1);
			
			
			ResultSet rs=pStmt.executeQuery();
			
			//リスト作成
			CreateItemListLogic cill=new CreateItemListLogic();
			itemList=cill.CreatItemList(rs);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return itemList;

	}
	
	
	//フリーワード＆メインカテゴリー検索
	public List<Item> findItemFreeWordAndMainId(String word,String mainId){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		//キーワードリスト作成
		word=word.replaceAll("　", " ");
		String[] split=word.split(" ");
		
		for(int i=0;i<split.length;i++) {
			split[i]="%"+split[i]+"%";
		}
		
		mainId=mainId+"%";
		List<Item> itemList=new ArrayList<>();
		CreateItemListLogic cill=new CreateItemListLogic();
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//NAMEを検索
			//SQL文を作成
			String sql1="SELECT * FROM ITEM WHERE DELETECHECK=0 AND IID LIKE ?";
			for(int i=0;i<split.length;i++) {
				if(i==0) {
					sql1=sql1+" AND";
				}else {
					sql1=sql1+" OR";
				}
				sql1=sql1+" NAME LIKE ?";
			}
			
			PreparedStatement pStmt1=conn.prepareStatement(sql1);
			
			pStmt1.setString(1, mainId);
			for(int i=0;i<split.length;i++) {
				pStmt1.setString(i+2, split[i]);
			}

			
			ResultSet rs1=pStmt1.executeQuery();
			
			//リスト作成			
			itemList=cill.CreatItemList(rs1);
			
			//INFORMATIONを検索
			//SQL文を作成
			String sql2="SELECT * FROM ITEM WHERE DELETECHECK=0 AND IID LIKE ?";
			for(int i=0;i<split.length;i++) {
				if(i==0) {
					sql2=sql2+" AND";
				}else {
					sql2=sql2+" OR";
				}
				sql2=sql2+" INFORMATION LIKE ?";
			}
			
			PreparedStatement pStmt2=conn.prepareStatement(sql2);
			
			pStmt2.setString(1, mainId);
			for(int i=0;i<split.length;i++) {
				pStmt2.setString(i+2, split[i]);
			}
			
			ResultSet rs2=pStmt2.executeQuery();
			
			//リスト作成
			cill.CreateFreeWordSearchList(rs2, itemList);
			
			//SPECを検索
			//SQL文を作成
			String sql3="SELECT * FROM ITEM WHERE DELETECHECK=0 AND IID LIKE ?";
			for(int i=0;i<split.length;i++) {
				if(i==0) {
					sql3=sql3+" AND";
				}else {
					sql3=sql3+" OR";
				}
				sql3=sql3+" SPEC LIKE ?";
			}
			
			PreparedStatement pStmt3=conn.prepareStatement(sql3);
			
			pStmt3.setString(1, mainId);
			for(int i=0;i<split.length;i++) {
				pStmt3.setString(i+2, split[i]);
			}
			
			ResultSet rs3=pStmt3.executeQuery();
			
			//リスト作成
			cill.CreateFreeWordSearchList(rs3, itemList);
			
			//MATERIALを検索
			String sql4="SELECT * FROM ITEM WHERE DELETECHECK=0 AND IID LIKE ?";
			for(int i=0;i<split.length;i++) {
				if(i==0) {
					sql4=sql4+" AND";
				}else {
					sql4=sql4+" OR";
				}
				sql4=sql4+" MATERIAL LIKE ?";
			}
			
			PreparedStatement pStmt4=conn.prepareStatement(sql4);
			
			pStmt4.setString(1, mainId);
			for(int i=0;i<split.length;i++) {
				pStmt4.setString(i+2, split[i]);
			}
			
			ResultSet rs4=pStmt4.executeQuery();
			
			//TAGを検索
			String sql5="SELECT * FROM ITEM WHERE DELETECHECK=0 AND IID LIKE ?";
			for(int i=0;i<split.length;i++) {
				if(i==0) {
					sql5=sql5+" AND";
				}else {
					sql5=sql5+" OR";
				}
				sql5=sql5+" TAG LIKE ?";
			}
			
			PreparedStatement pStmt5=conn.prepareStatement(sql5);
			
			pStmt5.setString(1, mainId);
			for(int i=0;i<split.length;i++) {
				pStmt5.setString(i+2, split[i]);
			}
			
			ResultSet rs5=pStmt5.executeQuery();

			//リスト作成
			cill.CreateFreeWordSearchList(rs5, itemList);
			
			List<String> removeList=new ArrayList<>();
			for(int in=0;in<itemList.size();in++) {
				String str="";
				int count=0;
				Item i=itemList.get(in);
				str=i.getName()+i.getInformation()+i.getMaterial()+i.getSpec()+i.getTag();
				for(String s:split) {
					s=s.replaceAll("%", "");
					if(str.contains(s)) {
						count++;
					}
				}
				if(count!=split.length) {
					removeList.add(i.getIID());
				}
			}
			for(String s:removeList) {
				int a=-1;
				for(Item i:itemList) {
					if(s.equals(i.getIID())) {
						a=itemList.indexOf(i);
					}
				}
				if(a!=-1) {
					itemList.remove(a);
				}
			}


		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return itemList;

	}

	//削除した商品をもとに戻す
	public boolean revive(String iID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}

		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="UPDATE ITEM SET DELETECHECK=0 WHERE IID=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setString(1, iID);
			
			int rs=pStmt.executeUpdate();
			
			if(rs!=1) {
				return false;
			}
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	//全商品取得
	public List<Item> findAllItem(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}

		List<Item> itemList=new ArrayList<>();
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="SELECT * FROM ITEM WHERE DELETECHECK=0";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			ResultSet rs=pStmt.executeQuery();
			
			CreateItemListLogic cill=new CreateItemListLogic();
			itemList=cill.CreatItemList(rs);
			return itemList;

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	//在庫０検索
	public List<Item> stockzero(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}

		List<Item> itemList=new ArrayList<>();
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="SELECT * FROM ITEM WHERE DELETECHECK=0 AND STOCK=0";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			ResultSet rs=pStmt.executeQuery();
			
			CreateItemListLogic cill=new CreateItemListLogic();
			itemList=cill.CreatItemList(rs);
			return itemList;

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//在庫変更
	public boolean stockEdit(String iId,int stock) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="UPDATE ITEM SET STOCK=STOCK+? WHERE IID=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setInt(1, stock);
			pStmt.setString(2, iId);
			
			int rs=pStmt.executeUpdate();
			if(rs!=1) {
				return false;
			}

			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
}


