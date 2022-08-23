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
import model.ItemSortLogic;

/**
 * Servlet implementation class ItemSortServlet
 */
@WebServlet("/ItemSortServlet")
public class ItemSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemSortServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sort=request.getParameter("sort");
		HttpSession session=request.getSession();
		List<Item> itemList=(List)session.getAttribute("itemList");
		ItemSortLogic isl=new ItemSortLogic();
		
		if(sort.equals("new")) {
			isl.sortByDate(itemList);
		}else if(sort.equals("buycount")) {
			isl.sortByBuyCount(itemList);
		}else if(sort.equals("highPrice")) {
			isl.sortByHighPrice(itemList);
		}else if(sort.equals("lowPrice")) {
			isl.sortByLowPrice(itemList);
		}
		
		session.setAttribute("itemList", itemList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
