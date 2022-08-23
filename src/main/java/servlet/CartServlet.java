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
import model.SumLogic;
import model.userDB;
/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ヘッダー・カートから飛んだ時
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		userDB user=(userDB)session.getAttribute("loginUser");

		//ヘッダー→カート
		if(action==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
			dispatcher.forward(request, response);
		//カート→購入確認orログイン
		}else if(action.equals("buy")) {
			//ログインしてるとき→購入確認
			if(user != null) {
				List<CartItem> cartList=(List)session.getAttribute("cartList");
				
				SumLogic sl=new SumLogic();
				int sum=sl.sumLogic(cartList);
				
				request.setAttribute("sum", sum);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyCheck.jsp");
				dispatcher.forward(request, response);
			//ログインしてないとき→ログイン画面
			}else {
				request.setAttribute("before", "cart");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//カート・商品詳細・ログインから飛んだ時
		//情報を取得
		String iId=request.getParameter("iId");
		String action=request.getParameter("action");
		String before=request.getParameter("before");
		
		//ログイン→購入確認
		if(before.equals("login")) {
			doGet(request,response);
		}
		
		HttpSession session=request.getSession();
		List<CartItem> cartList=(List)session.getAttribute("cartList");
		userDB user=(userDB)session.getAttribute("loginUser");
		
		//購入個数があるとき
		if(!request.getParameter("buyNum_").equals("") && request.getParameter("buyNum_")!=null) {
			int buyNum=Integer.parseInt(request.getParameter("buyNum_"));
			
			//カートリストを作成
			ItemDAO dao=new ItemDAO();
			Item item=dao.findOneItem(iId);
			
			CartItem ci=new CartItem(item,buyNum);
			
			HistoryDAO hDAO=new HistoryDAO();
			
			if(cartList!=null) {
				boolean checkCart=true;
				for(CartItem ci_:cartList) {
					if(ci_.getItem().getIID().equals(item.getIID())){
						ci_.setBuyNum(ci_.getBuyNum()+buyNum);
						hDAO.changeBuyNum(user, ci);
						
						checkCart=false;
						break;
					}
				}
				if(checkCart) {
					cartList.add(ci);
					
					if(user!=null) {
						hDAO.addCart(user, ci);
					}
				}
			}else {
				cartList=new ArrayList<CartItem>();
				cartList.add(ci);
				if(user!=null) {
					hDAO.addCart(user, ci);
				}
			}
			
			
			session.setAttribute("cartList",cartList);
			//カートリスト作成ここまで
		
			//カートに入れる　商品詳細→商品詳細
			if(action.equals("cart")) {
				request.setAttribute("iId", iId);
				
				String paging=request.getParameter("paging");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("ItemServlet?paging="+paging);
				dispatcher.forward(request, response);
			//すぐに購入　商品詳細→購入確認・ログイン
			}else {
				if(cartList!=null) {
					SumLogic sl=new SumLogic();
					int sum=sl.sumLogic(cartList);
					
					request.setAttribute("sum", sum);
					
					//ログインしてるとき　商品詳細→購入確認
					if(user != null) {
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyCheck.jsp");
					dispatcher.forward(request, response);
					//ログインしてないとき　商品詳細→ログイン
					}else {
						request.setAttribute("before", "cart");
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
					}
				}
				
			}
		}else {
			//カート→カートに戻る
			if(before.equals("cart")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
				dispatcher.forward(request, response);
			//商品詳細→商品詳細に戻る
			}else if(before.equals("item")) {
				request.setAttribute("iId", iId);
				
				String paging=request.getParameter("paging");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("ItemServlet?paging="+paging);
				dispatcher.forward(request, response);
			}
		}
		
	}

}
