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

import model.Item;
import model.SearchWord;

/**
 * Servlet implementation class SerachItemServlet
 */
@WebServlet("/SearchItemServlet")
public class SearchItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String mainId=null;
		String subId=null;
		String freeWord=null;
		
		request.setCharacterEncoding("UTF-8");
		mainId=request.getParameter("mainId");
		subId=request.getParameter("subId");
		freeWord=request.getParameter("freeWord");
		
		List<Item> itemList=new ArrayList<>();
		
		HttpSession session=request.getSession();
		
		SearchWord sw=new SearchWord();
		if(mainId!=null || subId!=null || freeWord!=null) {
			sw.setMainId(mainId);
			sw.setSubId(subId);
			sw.setFreeWord(freeWord);
			itemList=sw.findItem();
		}else {
			itemList=(List)session.getAttribute("itemList");
		}
		
		
		session.setAttribute("itemList", itemList);
		
		
		session.setAttribute("searchWord",sw);
		
		//response.sendRedirect("/WEB-INF/jsp/searchResult.jsp");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp");
		dispatcher.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mainId=null;
		String subId=null;
		String freeWord=null;
		
		request.setCharacterEncoding("UTF-8");
		mainId=request.getParameter("mainId");
		subId=request.getParameter("subId");
		freeWord=request.getParameter("freeWord");
		
		SearchWord sw=new SearchWord(mainId,subId,freeWord);
		List<Item> itemList=sw.findItem();
		
		HttpSession session=request.getSession();
		session.setAttribute("itemList", itemList);
		session.setAttribute("searchWord",sw);
		
		if(mainId != null || subId != null || freeWord.replaceAll("　| ", "") != "") {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp");
			dispatcher.forward(request, response);
	

	}else {
			String action = request.getParameter("action");
			if(action.equals("index")) {
				response.sendRedirect("index.jsp");
			}else {

				SearchWord word=new SearchWord(null,null,"犬");
				List<Item> itemList2 = word.findItem();		

				session.setAttribute("itemList2", itemList2);
				
				
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp");
					dispatcher.forward(request, response);

				}
	}
	}
}

