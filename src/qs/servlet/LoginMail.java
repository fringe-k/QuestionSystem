package qs.servlet;
import qs.common.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginMail")
public class LoginMail extends HttpServlet
{
    public void save(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {

        String email=request.getParameter("mail");
        String psw = request.getParameter("password");

        UserDao userdao = new UserDao();
        userdao.connect();
        try
        {
            User user =  userdao.selectByMail(email);
            if (user != null && user.getPassword().equals(psw))
            {
                if(user.getCannotLogin()==0)
            //登录成功
            {
                response.setContentType("text");
                PrintWriter out = response.getWriter();

                
               if(user.getRole()==0) {
                   HttpSession UserSession = request.getSession();
                   UserSession.setAttribute("user", user);//将登陆成功的用户保存于session
                   out.println("user");
               }
               else
               {
                   HttpSession AdSession = request.getSession();
                   AdSession.setAttribute("user", user);//将登陆成功的用户保存于session
                   out.println("manager");
               }
            }

            else
            {
                response.setContentType("text");
                PrintWriter out = response.getWriter();
                out.println("forbidden");
            }
            } else
            {
                response.setContentType("text");
                PrintWriter out = response.getWriter();
                out.println("error");

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {
        this.save(request,response);
    }
}
