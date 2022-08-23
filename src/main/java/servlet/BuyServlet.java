package servlet;

import java.io.IOException;
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
import DAO.ItemDAO;
import model.CartItem;
import model.Item;
import model.userDB;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		userDB lUser=(userDB)session.getAttribute("loginUser");
		List<CartItem> cartList=(List)session.getAttribute("cartList");
		
		ItemDAO iDAO=new ItemDAO();
		HistoryDAO hDAO=new HistoryDAO();
		
		//在庫数チェック
		boolean checkStock=true;
		List<String> errorMsgList=new ArrayList<>();
		for(CartItem ci:cartList) {
			Item item=iDAO.findOneItem(ci.getItem().getIID());
			int stock=item.getStock();
			if(stock<ci.getBuyNum()) {
				checkStock=false;
				String errorMsg=item.getName()+"の在庫が足りません。:在庫数"+stock;
				errorMsgList.add(errorMsg);
			}
		}
		
		//購入処理
		if(checkStock) {
			boolean buy=iDAO.buyItem(cartList);
			boolean addHistory=hDAO.addHistory(lUser, cartList);
			
			
			
			session.removeAttribute("cartList");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/orderOK.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("errorMsgList", errorMsgList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
			dispatcher.forward(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		userDB lUser=(userDB)session.getAttribute("loginUser");
		CartItem ci=(CartItem)session.getAttribute("BuyItem");
		
		ItemDAO iDAO=new ItemDAO();
		HistoryDAO hDAO=new HistoryDAO();
		
		//在庫数チェック
		boolean checkStock=true;
		List<String> errorMsgList=new ArrayList<>();
			Item item=iDAO.findOneItem(ci.getItem().getIID());
			int stock=item.getStock();
			if(stock<ci.getBuyNum()) {
				checkStock=false;
				String errorMsg=item.getName()+"の在庫が足りません。:在庫数"+stock;
				errorMsgList.add(errorMsg);
			}
		
		//購入処理
		if(checkStock) {
			List<CartItem> cartList=new ArrayList<>();
			cartList.add(ci);
			
			boolean buy=iDAO.buyItem(cartList);
			
			hDAO.addCart(lUser, ci);
			boolean addHistory=hDAO.addHistory(lUser, cartList);
			
			session.removeAttribute("BuyItem");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/orderOK.jsp");
			dispatcher.forward(request, response);
		}else {
			List<CartItem> cartList=(List)session.getAttribute("cartList");
			
			if(cartList!=null) {
				boolean checkCart=true;
				for(CartItem ci_:cartList) {
					if(ci_.getItem().getIID().equals(item.getIID())){
						ci_.setBuyNum(ci_.getBuyNum()+ci.getBuyNum());
						checkCart=false;
						break;
					}
				}
				if(checkCart) {
					cartList.add(ci);
				}
			}else {
				cartList=new ArrayList<CartItem>();
				cartList.add(ci);
			}
			session.setAttribute("cartList",cartList);

			
			request.setAttribute("errorMsgList", errorMsgList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
			dispatcher.forward(request, response);
		}

	}

}
