package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ItemDAO;
import model.CartItem;
import model.Item;
import model.userDB;

/**
 * Servlet implementation class BuyOneItem
 */
@WebServlet("/BuyOneItemServlet")
public class BuyOneItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyOneItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String iId=request.getParameter("iId");
		int buyNum=Integer.parseInt(request.getParameter("buyNum_"));
		
		ItemDAO dao=new ItemDAO();
		Item item=dao.findOneItem(iId);
		
		CartItem ci=new CartItem(item, buyNum);
		
		HttpSession session=request.getSession();
		session.setAttribute("BuyItem", ci);
		request.setAttribute("before", "one");
		request.setAttribute("sum", buyNum*item.getPrice());
		
		userDB user=(userDB)session.getAttribute("loginUser");
		
		if(user==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyCheck.jsp");
			dispatcher.forward(request, response);
		}
	}

}
