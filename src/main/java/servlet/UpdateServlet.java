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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	doPost(request,response);
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
            	HttpSession session=request.getSession();
            	userDB user=(userDB)session.getAttribute("loginUser");
                Database db = new Database();
                db.update(username, password, mail, address,user);
                out.println("更新成功");
                
                user=db.login(username, password);
                session.setAttribute("loginUser", user);
                
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
        		dispatcher.forward(request, response);
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

