package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CartItem;
import model.Item;
import model.userDB;

public class HistoryDAO {
	//フィールド
	private final String JDBC_URL ="jdbc:mysql://localhost:3306/n_g?characterEncoding=UTF-8&serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "";
	
	//メソッド
	//購入履歴追加
	public boolean addHistory(userDB user,List<CartItem> cartList) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			for(CartItem ci:cartList) {
				String sql="UPDATE HISTORY SET BUYED=1 WHERE IID=?";
				PreparedStatement pStmt=conn.prepareStatement(sql);
				
				pStmt.setString(1, ci.getItem().getIID());
				
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
	
	//購入履歴を取得
	public List<CartItem> findHistory(userDB user){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		List<CartItem> historyList=new ArrayList<>();
		
			try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
				
				String sql1="SELECT IID,BUYNUM FROM HISTORY WHERE BUYED=1 AND UID=?";
				PreparedStatement pStmt1=conn.prepareStatement(sql1);
				
				pStmt1.setInt(1, user.getUID());
				
				ResultSet rs1=pStmt1.executeQuery();
				
				ItemDAO iDAO=new ItemDAO();
				
				while(rs1.next()) {
					String iID=rs1.getString("IID");
					int buyNum=rs1.getInt("BUYNUM");
					
					Item item=iDAO.findOneItem(iID);
					
					CartItem ci=new CartItem(item,buyNum);
					historyList.add(ci);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}

			return historyList;
		
	}
	
	//売上履歴を取得（管理者用）
	public List<CartItem> findAllHistory(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		List<CartItem> historyList=new ArrayList<>();
		
			try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
				
				String sql1="SELECT IID,BUYNUM FROM HISTORY WHERE BUYED=1";
				PreparedStatement pStmt1=conn.prepareStatement(sql1);
				
				ResultSet rs1=pStmt1.executeQuery();
				
				ItemDAO iDAO=new ItemDAO();
				
				while(rs1.next()) {
					String iID=rs1.getString("IID");
					int buyNum=rs1.getInt("BUYNUM");
					
					Item item=iDAO.findOneItem(iID);
					
					CartItem ci=new CartItem(item,buyNum);
					historyList.add(ci);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}

			return historyList;
		
	}
	
	//カートに追加
	public boolean addCart(userDB user,CartItem ci) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
				String sql="INSERT INTO History(UID,IID,BUYNUM) VALUE(?,?,?)";
				PreparedStatement pStmt=conn.prepareStatement(sql);
				
				pStmt.setInt(1, user.getUID());
				pStmt.setString(2, ci.getItem().getIID());
				pStmt.setInt(3, ci.getBuyNum());
				
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
	
	//カートを取得
	public List<CartItem> findCart(userDB user){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}

		List<CartItem> cartList=new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql1="SELECT IID,BUYNUM FROM HISTORY WHERE BUYED=0 AND UID=?";
			PreparedStatement pStmt1=conn.prepareStatement(sql1);
			pStmt1.setInt(1, user.getUID());
			
			ResultSet rs1=pStmt1.executeQuery();
			
			ItemDAO iDAO=new ItemDAO();
			
			while(rs1.next()) {
				String iID=rs1.getString("IID");
				int buyNum=rs1.getInt("BUYNUM");
				
				Item item=iDAO.findOneItem(iID);
				
				CartItem ci=new CartItem(item,buyNum);
				cartList.add(ci);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

		return cartList;
		
	}
	
	//購入個数追加
	public boolean changeBuyNum(userDB user,CartItem ci) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql="UPDATE HISTORY SET BUYNUM=? WHERE BUYED=0 AND UID=? AND IID=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setInt(1, ci.getBuyNum());
			pStmt.setInt(2, user.getUID());
			pStmt.setString(3, ci.getItem().getIID());
			
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
	
	//カートから削除
	public boolean deleteCart(userDB user,CartItem ci) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql="DELETE FROM HISTORY WHERE BUYED=0 AND UID=? AND IID=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setInt(1, user.getUID());
			pStmt.setString(2, ci.getItem().getIID());
			
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
	
	//購入個数変更
	public boolean changeBuyNum2(userDB user,CartItem ci,int buyNum) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql="UPDATE HISTORY SET BUYNUM=? WHERE BUYED=0 AND UID=? AND IID=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setInt(1, buyNum);
			pStmt.setInt(2, user.getUID());
			pStmt.setString(3, ci.getItem().getIID());
			
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
