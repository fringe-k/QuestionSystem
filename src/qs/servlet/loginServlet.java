package qs.servlet;

import qs.common.User;
import qs.common.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.io.*;
import com.alibaba.fastjson.*;


@WebServlet(name = "login", urlPatterns = "/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {

        String email=request.getParameter("mail");
        String psw = request.getParameter("password");


//        // test
//        email = new String("123456");
//        psw = new String("123456");
//        response.setContentType("text/html");

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        JSONObject json = new JSONObject();
        UserDao userdao = new UserDao();

        PrintWriter out = response.getWriter();
        try
        {
            User user =  userdao.selectByMail(email);
            if (user != null && user.getPassword().equals(psw))
            {
                json.put("message","Login successful.");
//                session.setAttribute("response",json.toJSONString());
                out.print(json.toJSONString());
            } else
            {
                json.put("message","Login failed. Password is wrong!");
//                session.setAttribute("response",json.toJSONString());
                out.print(json.toJSONString());
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
