package qs.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qs.common.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet("/G-A")

public class giveLikeAndAwarding extends HttpServlet
{
    public void getInformationGiveLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Long qid = Long.valueOf(request.getParameter("questionId"));
        String mail = request.getParameter("email");
        System.out.println(mail);
        String orderBy = request.getParameter("orderBy");

        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject json = new JSONObject();

            UserDao LoginUserdao=new UserDao();
            LoginUserdao.connect();
            User LoginUser=LoginUserdao.selectByMail(mail);

            AnswerDao adao = new AnswerDao();
            adao.connect();
            //ArrayList<Answer> answer = adao.selectByQuestionId(qid);

            if (orderBy == null)
                orderBy = "time";
            if(orderBy.equals("numOfAgree")) orderBy = " numOfAgree desc ";
            String sql2 = " select Answer.id from Answer  where questionId = " + qid +
                    " and Answer.isReleased = 1 order by " + orderBy;
            adao.excuteQuery(sql2);
            ArrayList<Answer> answers= new ArrayList<Answer>();
            ResultSet rs = adao.getResultSet();
            while(rs.next()){
                Long id = rs.getLong(1);
                Answer answer=adao.selectById(id);
                answers.add(answer);
            }



            for(int i=0;i<answers.size();i++) {

                Long answerId=answers.get(i).getId();
                String sql = " select count(*) from Agree where answerId = " + answerId + " and userId = " + LoginUser.getId();
                AgreeDao agreedao = new AgreeDao();
                agreedao.connect();
                agreedao.excuteQuery(sql);
                ResultSet rs2 = agreedao.getResultSet();
                //boolean ok=true;
                if (rs2.next()) {
                    json.put("ok",false);
                    out.println("already givelike");

                }
                else
                {
                    json.put("ok",true);
                    out.println("isok");
                }
                jsonArray.add(json);
            }
            out.println(jsonArray.toJSONString());
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void getInformationAwarding(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        Long questionId= Long.valueOf(req.getParameter("questionId"));
        String mail = req.getParameter("email");

        try {

            QuestionDao qdao=new QuestionDao();
            qdao.connect();
            Question question=qdao.selectById(questionId);
            out.println(question.getAlreadyAward());



        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            this.getInformationAwarding(request,response);
        }
        catch(Exception e) {
            System.out.println("error!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            this.getInformationGiveLike(request,response);
        }
        catch(Exception e) {
            System.out.println("error");
        }
    }
}
