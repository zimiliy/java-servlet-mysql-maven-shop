package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.Database;
import model.userDB;

@WebServlet("/DelServlet")
public class DelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //文字化け防止
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
        	HttpSession session=request.getSession();
        	userDB user=(userDB)session.getAttribute("loginUser");
        	
            Database db = new Database();
            db.delete(user);
            
            session.removeAttribute("loginUser");
            
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDeleteOK.jsp");
			dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("削除失敗");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("削除失敗");
        }
    }
}
