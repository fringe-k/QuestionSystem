package qs.servlet;

import com.mysql.cj.xdevapi.Session;
import qs.common.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.sql.*;
import com.alibaba.fastjson.*;


@WebServlet("/getQuestionByType")
public class GetQuestionByType extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        final int numPerReponse = 5;    // 默认一次回复5个问题内容

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String type = req.getParameter("questionType");
        type = URLDecoder.decode(type,"UTF-8");
        int index = Integer.parseInt(req.getParameter("index"));


//        // for debug
//        String type = new String("科学");
//        int index = 0;




        // 创建数据访问对象
        QuestionDao qdao = new QuestionDao();
        QuestionTypeDao qtdao = new QuestionTypeDao();
        UserDao udao = new UserDao();
        AnswerDao adao = new AnswerDao();

        // 创建JSON数组对象
        JSONArray jsonArray = new JSONArray();
        // 创建输出流
        PrintWriter out = resp.getWriter();

        try {
            // 寻找对应问题类型的类型id
            Long typeId = qtdao.selectByName(type).getId();
            // 寻找对应问题类型的问题
            ArrayList<Question> questions = qdao.selectByTypeId(typeId);

            for (int i = index; i < index + numPerReponse && i < questions.size(); i++){

                // 创建JSON对象
                JSONObject json = new JSONObject();

                Question question = questions.get(i);
                User user = udao.selectById(question.getUserID());

                json.put("title",question.getTitle());
                json.put("Questioner",user.getName());
                json.put("date",question.getTime().toGMTString());
                json.put("numOfview",question.getFrequency().intValue());
                json.put("numOfAnswer", adao.countByQuestionId(question.getId()));

                jsonArray.add(json);
            }

            // for debug
            System.out.print(jsonArray.toJSONString());
            out.print(jsonArray.toJSONString());

        }catch (SQLException e){
            System.out.print(e.getMessage());
        }

    }
}


