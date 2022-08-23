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

import DAO.ItemDAO;
import model.Item;
import model.SearchWord;

/**
 * Servlet implementation class StockEditServlet
 */
@WebServlet("/StockEditServlet")
public class StockEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemDAO dao=new ItemDAO();
		
		HttpSession session=request.getSession();
		List<Item> itemList=(List)session.getAttribute("itemList");

		for(int i=0;i<itemList.size();i++) {
			Integer num=Integer.valueOf(i);
			String num_=num.toString();
			String s=request.getParameter(num_);
			if(!s.isEmpty()) {
				int stock=Integer.parseInt(s);
				String key=itemList.get(i).getIID();
				String iId=request.getParameter(key);
				
				dao.stockEdit(iId, stock);
			}
		}
		
		SearchWord sw=(SearchWord)session.getAttribute("searchWord");
		itemList=sw.findItem();
		
		session.setAttribute("itemList",itemList);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminResult.jsp");
		dispatcher.forward(request, response);

	}

}
