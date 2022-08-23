package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.ItemDAO;
import model.ExtensionLogic;
import model.Item;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/EditItemServlet")
@MultipartConfig
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String iId = request.getParameter("iId");
		
		ItemDAO dao=new ItemDAO();
		
		Item i=dao.findOneItem(iId);
		
		request.setAttribute("name",i.getName());
		
		Integer p=Integer.valueOf(i.getPrice());
		String price=p.toString();
		request.setAttribute("price",price);
		
		Integer s=Integer.valueOf(i.getStock());
		String stock=s.toString();
		request.setAttribute("stock",stock);
		
		request.setAttribute("information",i.getInformation());
		request.setAttribute("spec",i.getSpec());
		request.setAttribute("material",i.getMaterial());
		request.setAttribute("iId",i.getIID());
		request.setAttribute("tag",i.getTag());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Part part=request.getPart("foto");
		if(!request.getParameter("name").equals("") &&  
				!request.getParameter("price").equals("") && 
				!request.getParameter("stock").equals("") 
				&& !request.getParameter("information").equals("")
				) {

			//変数に変換
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			String information = request.getParameter("information");
			String spec = request.getParameter("spec");
			String material = request.getParameter("material");
			String iId = request.getParameter("iId");
			String tag = request.getParameter("tag");
			
			ItemDAO dao = new ItemDAO();
			String filename;
			
			
			ExtensionLogic el=new ExtensionLogic();
			
			//画像が選択されている場合
			if(el.Extension(part)) {
				filename=part.getSubmittedFileName();
				
				String[] split=filename.split(Pattern.quote("."));
				List<String> imageList=dao.findImageName(split[0]);
				
				
				if(imageList!=null) {
					int size=imageList.size();
					filename=split[0]+"("+(size+1)+")."+split[1];
				}
				
				part.write("C:"+File.separator+"servlet"+File.separator+"N_G"+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"upload"+File.separator+filename);
			//画像が選択されていない場合
			}else {
				Item item=dao.findOneItem(iId);
				filename=item.getImage();
			}
			
			dao.editItem(name, price, filename,information, spec, material, stock, iId,tag);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editItemOK.jsp");
			dispatcher.forward(request, response);
		}else{
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String stock = request.getParameter("stock");
			String information = request.getParameter("information");
			String spec = request.getParameter("spec");
			String material = request.getParameter("material");
			String iId = request.getParameter("iId");
			String tag = request.getParameter("tag");
			
			//HttpSession session = request.getSession();
		
			request.setAttribute("name", name);
			request.setAttribute("price", price);
			request.setAttribute("stock", stock);
			request.setAttribute("information", information);
			request.setAttribute("spec", spec);
			request.setAttribute("material", material);
			request.setAttribute("part", part);
			request.setAttribute("iId", iId);
			request.setAttribute("tag",tag);
	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp");
			dispatcher.forward(request, response);
		}
		
	}


}
