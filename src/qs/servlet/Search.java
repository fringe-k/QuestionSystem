package qs.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet("/search")
public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
//        action = new String(action.getBytes("ISO-8859-1"),"UTF-8");
        String entity = req.getParameter("entity");
        System.out.println(entity);
        entity = new String(entity.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(entity);
        out.print("sfdasdf高富帅的告诉对方");

//        if(action.equals("select") && entity.equals("Question")){
//            doSelectQuestion(req, resp);
//        }else if(action.equals("select") && entity.equals("Answer")){
//            doSelectAnswer(req,resp);
//        }else{
//            out.print("Access refused!");
//        }
    }

    protected void doSelectQuestion(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String keywords = URLDecoder.decode(req.getParameter("keywords"), "UTF-8");
    }

    protected void doSelectAnswer(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String keywords = URLDecoder.decode(req.getParameter("keywords"), "UTF-8");
    }

}
