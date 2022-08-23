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

@WebServlet("/Upload")
@MultipartConfig
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addItem.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Part part=request.getPart("foto");
		
		ExtensionLogic el=new ExtensionLogic();

		if(request.getParameter("name") != null && request.getParameter("mainid") != null && 
				request.getParameter("subid") != null && request.getParameter("price") != null && 
				request.getParameter("stock") != null && request.getParameter("information") != null &&
				//request.getParameter("spec") != null && request.getParameter("material") != null && 
				el.Extension(part)) {

			//変数に変換
			String name = request.getParameter("name");
			String mainid = request.getParameter("mainid");
			String subid = request.getParameter("subid");
			int price = Integer.parseInt(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			String information = request.getParameter("information");
			String spec = request.getParameter("spec");
			String material = request.getParameter("material");
			String tag = request.getParameter("tag");
			
			String filename=part.getSubmittedFileName();
			
			System.out.println(filename);
			
			ItemDAO dao = new ItemDAO();
			String[] split=filename.split(Pattern.quote("."));
			List<String> imageList=dao.findImageName(split[0]);
			
			
			
			if(imageList!=null) {
				int size=imageList.size();
				filename=split[0]+"("+(size+1)+")."+split[1];
			}
			
			part.write("C:"+File.separator+"servlet"+File.separator+"N_G"+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"upload"+File.separator+filename);
			
			dao.addItem(mainid, subid, name, price, filename,information, spec, material,stock,tag);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addItemOK.jsp");
		dispatcher.forward(request, response);
		}else{
			
			String name = request.getParameter("name");
			String mainid = request.getParameter("mainid");
			String subid = request.getParameter("subid");
			String price = request.getParameter("price");
			String stock = request.getParameter("stock");
			String information = request.getParameter("information");
			String spec = request.getParameter("spec");
			String material = request.getParameter("material");
			String tag = request.getParameter("tag");
			
			request.setAttribute("name", name);
			request.setAttribute("mainid", mainid);
			request.setAttribute("subid", subid);
			request.setAttribute("price", price);
			request.setAttribute("stock", stock);
			request.setAttribute("information", information);
			request.setAttribute("spec", spec);
			request.setAttribute("material", material);
			request.setAttribute("part", part);
			request.setAttribute("tag", tag);
			

			//エラーメッセージの処理
			if(name != null && name.length() != 0) {
			}else {request.setAttribute("msgn", "入力されていません");}	
			if(price != null && price.length() != 0) {
			}else {request.setAttribute("msgp", "入力されていません");}	
			if(stock != null && stock.length() != 0) {
			}else {request.setAttribute("msgst", "入力されていません");}	
			if(information != null && information.length() != 0) {
			}else {request.setAttribute("msgi", "入力されていません");}	
			if(spec != null && spec.length() != 0) {
			}else {request.setAttribute("msgsp", "入力されていません");}	
			if(material != null && material.length() != 0) {
			}else {request.setAttribute("msgm", "入力されていません");}	
			if(part != null && part.getSize() != 0) {
			}else {request.setAttribute("msgpart", "入力されていません");}
			if(tag != null && tag.length() != 0) {
			}else {request.setAttribute("msgt", "入力されていません");}
			
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addItem.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
