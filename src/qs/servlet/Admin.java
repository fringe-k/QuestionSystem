package qs.servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import com.alibaba.fastjson.*;
import com.mysql.cj.xdevapi.Session;
import qs.common.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Set;

@WebServlet("/admin")
public class Admin extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        switch (action){
            case "select": doSelect(req, resp); break;
            case "insert": doInsert(req, resp); break;
            case "update": doUpdate(req, resp); break;
            case "delete": doDelete(req, resp); break;
        }

    }

    protected void doSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        DAO dao = new DAO();
        PrintWriter out = resp.getWriter();
        String entity = req.getParameter("entity");
        try {
            // 如果是查询用户，直接给出全部用户信息  !注意规定管理员不可管理管理员的信息
            if (entity.equals("User"))
                dao.excuteQuery("select id,name,mail,cannotSpeak,cannotLogin from User where role = 0");

            // 如果是查询问题类型，直接给出全部问题类型
            if(entity.equals("QuestionType"))
                dao.excuteQuery("select * from QuestionType");

            // 如果是查询对应问题类型的问题
            // 如果没有传输问题类型，则默认给出全部问题列表，否则给出对应问题类型的问题
            if(entity.equals("Question")){
                String type = req.getParameter("type");
                String sql = new String("select id,title,label,time from Question");
                if(type != null) sql += "where type = " + type;
                dao.excuteQuery(sql);
            }

            if(entity.equals("Label")){
                dao.excuteQuery("select Label.*, (select count(*) from Q_L where labelId=Label.id) count from Label");

            }
            if(dao.getResultSet() != null)
                out.print(dao.toJsonArray().toJSONString());
            else out.print("error!");

        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    protected void doInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        String entity = req.getParameter("entity");
        try{
            // 插入新的问题类型
            if(entity.equals("QuestionType")){
                // 获取新的问题类型的名字
                String name = req.getParameter("name");
                QuestionTypeDao qtdao = new QuestionTypeDao();
                Long id = qtdao.createQuestionType(name);
                out.print("success");
            }
            if(entity.equals("Label")){
                // 获取新的问题类型的名字
                String name = req.getParameter("name");
                LabelDao ldao = new LabelDao();
                Long id = ldao.createLabel(name);
                out.print("success");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    protected void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String entity = req.getParameter("entity");
        Long id = Long.valueOf(req.getParameter("id"));
        PrintWriter out = resp.getWriter();
        try{
            if(entity.equals("User")){

                UserDao udao = new UserDao();
                String []paramters = {"cannotSpeak", "cannotLogin"};
                for(int i = 0; i < paramters.length; i++){
                    if(req.getParameter(paramters[i]) != null) {
                        Long value = Long.valueOf(req.getParameter(paramters[i]));
                        udao.update(id,paramters[i],value);
                    }
                }

            }else if(entity.equals("QuestionType")){
                QuestionTypeDao qtdao = new QuestionTypeDao();
                String name = req.getParameter("name");
                qtdao.update(id, "name",name);
            }else if(entity.equals("Label")){
                LabelDao ldao = new LabelDao();
                String name = req.getParameter("name");
                ldao.update(id, "name", name);
            }
            out.print("success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            out.print("failure");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String entity = req.getParameter("entity");
        String id = req.getParameter("id");
        DAO dao = new DAO();
        try {
            if(entity.equals("User")) {
                if (dao.excuteUpdate("delete from User where id = " + id))
                    out.print("success");
                else out.print("failure");
            }
            if(entity.equals("QuestionType")) {
                if (dao.excuteUpdate("delete from QuestionType where id = " + id))
                    out.print("success");
                else out.print("failure");
            }
            if(entity.equals("Label")) {
                if (dao.excuteUpdate("delete from Label where id = " + id))
                    out.print("success");
                else out.print("failure");
            }

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }



    }
}
