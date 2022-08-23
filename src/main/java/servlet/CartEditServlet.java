package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.HistoryDAO;
import model.CartItem;
import model.userDB;

/**
 * Servlet implementation class CartEditServlet
 */
@WebServlet("/CartEditServlet")
public class CartEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		List<CartItem> cartList=(List)session.getAttribute("cartList");
		
		
		//カートから削除
		int index=Integer.parseInt(request.getParameter("deleteIndex"));
		CartItem ci=cartList.get(index);
		HistoryDAO hDAO =new HistoryDAO();
		
		userDB user=(userDB)session.getAttribute("loginUser");
		
		if(user!=null) {
			hDAO.deleteCart(user,ci);
		}
		
		cartList.remove(index);
		session.setAttribute("cartList", cartList);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		List<CartItem> cartList=(List)session.getAttribute("cartList");
		userDB user=(userDB)session.getAttribute("loginUser");
		
		//購入個数を編集
		HistoryDAO hDAO=new HistoryDAO();
		
		if(user != null) {
			for(int i=0;i<cartList.size();i++) {
				Integer num=Integer.valueOf(i);
				String num_=num.toString();
				if(!num_.isEmpty()) {
					int buyNum=Integer.parseInt(request.getParameter(num_));
					hDAO.changeBuyNum2(user, cartList.get(i),buyNum);
					
					cartList.get(i).setBuyNum(buyNum);
					
				}
			}

		}else {
			for(int i=0;i<cartList.size();i++) {
				Integer num=Integer.valueOf(i);
				String num_=num.toString();
				if(!num_.isEmpty()) {
					int buyNum=Integer.parseInt(request.getParameter(num_));
					
					cartList.get(i).setBuyNum(buyNum);
				}
			}
		}
		session.setAttribute("cartList",cartList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);

	}
}
