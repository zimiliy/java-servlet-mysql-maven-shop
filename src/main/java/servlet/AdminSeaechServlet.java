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

import model.Item;
import model.SearchWord;

/**
 * Servlet implementation class AdminSeaechServlet
 */
@WebServlet("/AdminSeaechServlet")
public class AdminSeaechServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSeaechServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String before=request.getParameter("before");
		
		if(before==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminsearch.jsp");
			dispatcher.forward(request, response);
		}else {
			HttpSession session=request.getSession();
			SearchWord sw=(SearchWord)session.getAttribute("searchWord");
			
			List<Item> itemList=sw.findItem();
			
			session.setAttribute("itemList", itemList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminResult.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String freeWord=request.getParameter("keyword");
		String mainId=request.getParameter("mainId");
		String subId=null;
		
		SearchWord sw=new SearchWord(mainId,subId,freeWord);
		List<Item> itemList=sw.findItem();
		
		HttpSession session=request.getSession();
		session.setAttribute("itemList", itemList);
		session.setAttribute("searchWord", sw);
		
		if(mainId != null || subId != null || freeWord.replaceAll("　| ", "") != "") {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminResult.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminsearch.jsp");
			dispatcher.forward(request, response);
		}

	}

}
