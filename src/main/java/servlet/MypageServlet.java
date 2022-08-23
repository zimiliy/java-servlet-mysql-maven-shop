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



@WebServlet("/MypageServlet")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		userDB user=(userDB)session.getAttribute("loginUser");
		
		if(user != null) {
		
		HistoryDAO dao=new HistoryDAO();
		List<CartItem> historyList=dao.findHistory(user);
		
		session.setAttribute("historyList", historyList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
		
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginServlet");
			dispatcher.forward(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
