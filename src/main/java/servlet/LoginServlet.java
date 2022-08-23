package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.HistoryDAO;
import db.Database;
import model.CartItem;
import model.userDB;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //文字化け防止
    	
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
   String userName = request.getParameter("username");
   String password = request.getParameter("password");
   PrintWriter out = response.getWriter();
//   out.println("ユーザー名：" + userName +"パスワード：" + password);
        try {
            Database db = new Database();
            userDB user =db.login(userName,password);
            String before=(String)request.getParameter("before");
            if(user!=null){
            	HttpSession session=request.getSession();
            	session.setAttribute("loginUser",user);
            	
            	//ログイン前にカートに保存したやつ	・途中で書き換わるやつ
            	List<CartItem> cartList3=(List)session.getAttribute("cartList");
            	
            	HistoryDAO hDAO=new HistoryDAO();
            	//DBのカートを取得したやつ・途中で書き換わらんやつ
            	List<CartItem> cartList_=hDAO.findCart(user);
            	
            	if(cartList3==null) {
            		cartList3=new ArrayList<>();
            	}
            	
            	//DBのカートを取得したやつを複製したやつ・途中で書き換わらんやつ
            	List<CartItem> cartList=hDAO.findCart(user);
            	if(cartList==null) {
            		cartList=new ArrayList<>();
            	}
            	
            	
            	//DBをセッションスコープに取得
            	if(cartList_.size()!=0) {
	            	for(CartItem ci_:cartList_) {
	            		boolean checkCart=true;
	            		for(CartItem ci:cartList3) {
							if(ci_.getItem().getIID().equals(ci.getItem().getIID())){
								ci.setBuyNum(ci.getBuyNum()+ci_.getBuyNum());
								hDAO.changeBuyNum(user, ci);
								
								checkCart=false;
								break;
							}
						}
						if(checkCart) {
							cartList3.add(ci_);
		            	}
	            	}
	            session.setAttribute("cartList",cartList3);
            	}
            	
            	//カートをDBに保存
            	//ログイン前にカートに保存した商品があるとき
            	if(cartList3.size()!=0) {
            		//DBにカート情報があるとき
            		if(cartList.size()!=0) {
	            		for(CartItem ci:cartList) {
	            			boolean checkCart=true;
	            			for(CartItem ci_:cartList3){
								if(ci_.getItem().getIID().equals(ci.getItem().getIID())){
									checkCart=false;
									break;
								}
								if(checkCart) {
									hDAO.addCart(user, ci);
								}
	            			}
	            		}
	            	//DBにカート情報がないとき
            		}else {
            			for(CartItem ci:cartList3) {
            				hDAO.addCart(user,ci);
            			}
            		}
            	}
            	
            	
            	
            	//管理者のとき
            	if(user.getUID()==1) {
        			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
        			dispatcher.forward(request, response);
        		//一般ユーザーのとき
            	}else {
            		if(before.equals("cart")) {
            			RequestDispatcher dispatcher = request.getRequestDispatcher("CartServlet?action=buy&before=login");
            			dispatcher.forward(request, response);
            		}else if(before.equals("one")) {
            			CartItem ci=(CartItem)session.getAttribute("BuyItem");
            			
            			RequestDispatcher dispatcher = request.getRequestDispatcher("BuyOneItemServlet?iId="+ci.getItem().getIID()+"&buyNum_="+ci.getBuyNum());
            			dispatcher.forward(request, response);
            		}else {		            	
		                response.sendRedirect("index.jsp");
            		}
            	}
            }else {
            	response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("ログイン失敗");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("ログイン失敗");
        }
    }

    }

