package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.Database;
import model.userDB;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String mail = request.getParameter("mail");
        String address = request.getParameter("address");
        PrintWriter out = response.getWriter();
        if (password.equals(password2)) {
            try {
                Database db = new Database();
                db.insert(username, password, mail, address);
                out.println("登録成功");
                
                userDB user=db.login(username, password);
                
            	HttpSession session=request.getSession();
            	session.setAttribute("loginUser",user);
            	
            	 response.sendRedirect("index.jsp");              
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                out.println("登録失敗");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("登録失敗2");
            }

        }else {
            out.println("パスワードが一致しません");
            out.println("<a href=\"signUp.jsp\">登録ページに戻る</a>");
        }
    }
}