package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.userDB;

public class Database {
    public static Connection conn = null;

    public Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/n_g?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "");
        // System.out.println("12345");
    }

    public static void showConnect() {
        if (conn != null) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }


    }

    //关闭连接
    public static void close() throws SQLException {
        conn.close();
    }

    //插入数据
    public static void insert(String username, String password, String mail, String address) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("insert into n_g.user values(null,?,?,?,?)");
        prep.setString(1, password);
        prep.setString(2, username);
        prep.setString(3, mail);
        prep.setString(4, address);
        prep.execute();

    }
    public ArrayList<userDB> getAlluser() throws SQLException {
        ArrayList<userDB> uselist = new ArrayList<userDB>();
        PreparedStatement prep = conn.prepareStatement("select * from n_g.user");
prep.execute();
ResultSet rs = prep.executeQuery();
while(rs.next()){
    int myid = rs.getInt("UID");
    String myusername = rs.getString("NAME");
    String mypassword = rs.getString("PASS");
    String mymail = rs.getString("MAIL");
    String myaddress = rs.getString("ADDRESS");
uselist.add(new userDB(myid,myusername,mypassword,mymail,myaddress));
}
        return uselist;
    }
    public userDB getUser(String name) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("select * from n_g.user where NAME = ?");
        prep.setString(1, name);
        prep.execute();
        ResultSet rs = prep.executeQuery();
        if (rs.next()){
            int myid = rs.getInt("UID");
            String myusername = rs.getString("NAME");
            String mypassword = rs.getString("PASS");
            String mymail = rs.getString("MAIL");
            String myaddress = rs.getString("ADDRESS");
            return new userDB(myid,myusername,mypassword,mymail,myaddress);
        }
        return null;
    }
    public userDB login(String name, String password) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("select n_g.user.PASS from n_g.user where NAME = ?");
        prep.setString(1, name);

        prep.execute();
        ResultSet rs = prep.executeQuery();
    if(rs.next()){
    String mypassword = rs.getString("PASS");
    if(password.equals(mypassword)){
        return getUser(name);
    }else {
        return null;
    }

}
       else return null;
    }
    public void update(String username,String password,String mail,String address,userDB user) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("update n_g.user set NAME = ?,PASS = ?,MAIL = ?,ADDRESS = ? where UID = ?");
        prep.setString(1, username);
        prep.setString(2, password);
        prep.setString(3, mail);
        prep.setString(4, address);
        prep.setInt(5, user.getUID());

        prep.execute();

    }


    public void delete(userDB user) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("delete from n_g.user where UID = ?");
        prep.setInt(1, user.getUID());
        prep.execute();
    }
}