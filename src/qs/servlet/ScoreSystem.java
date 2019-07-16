package qs.servlet;

//import org.json.JSONObject;
import qs.common.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet("/ScoreSystem")
public class ScoreSystem extends HttpServlet
{
    //悬赏积分
    public void giveScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long questionId = Long.valueOf(request.getParameter("questionId"));
        Long answerId=Long.valueOf(request.getParameter("answerId"));

        try {
            String sql=" select userId from Answer where id = " + answerId + " and questionId = " + questionId ;
            AnswerDao adao = new AnswerDao();
            adao.connect();
            adao.excuteQuery(sql);
            ResultSet rs = adao.getResultSet();
            Long userId=0L;
            while(rs.next()) {
                userId = rs.getLong(1);

            }

            QuestionDao qdao=new QuestionDao();
            Question question=qdao.selectById(questionId);

            UserDao userdao=new UserDao();
            userdao.connect();
            User user=userdao.selectById(userId);


            Long id = userId;
            user.setName(user.getName());
            user.setPhoto(user.getPhoto());
            user.setMail(user.getmail());
            user.setPhone(user.getPhone());
            user.setAge(user.getAge());
            user.setId(user.getId());
            user.setPassword(user.getPassword());
            user.setRole(user.getRole());
            user.setScore(user.getScore()+question.getReward());
            user.setCannotLogin(user.getCannotLogin());
            user.setCannotSpeak(user.getCannotSpeak());
            user.setNumOfAnswer(user.getNumOfAnswer());
            user.setNumOfQuery(user.getNumOfQuery());

            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();


            if(question.getAlreadyAward()==0L) {
                out.println("ok");
                question.setAlreadyAward(1L);//标记为：已被悬赏
                qdao.update(question);

            }
            else
            {
                out.println("repeat");
            }

            userdao.connect();
            if (!userdao.update(user)) {

                out.println("unsuccessfully");

            } else {
                out.println("successfully");
            }

        }catch(Exception e)
        {
            e.getMessage();
        }
    }

    //点赞
    public void giveLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Long questionId = Long.valueOf(request.getParameter("questionId"));
        Long answerId=Long.valueOf(request.getParameter("answerId"));
        String mail=request.getParameter("email");

        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            String sql=" select userId from Answer where id = " + answerId  ;//" and questionId = " + questionId ;
            AnswerDao answerdao = new AnswerDao();
            answerdao.connect();
            answerdao.excuteQuery(sql);
            ResultSet rs = answerdao.getResultSet();
            Long userId=0L;
            while(rs.next()) {
                userId = rs.getLong(1);

            }

            Answer answer=answerdao.selectById(answerId);
            answer.setNumOfAgree(answer.getNumOfAgree()+1);

            UserDao LoginUserdao=new UserDao();
            User LoginUser=LoginUserdao.selectByMail(mail);
            String sql2=" select count(*) from Agree where answerId = " + answerId  + " and userId = " + LoginUser.getId();
            AgreeDao agreedao = new AgreeDao();
            agreedao.connect();
            agreedao.excuteQuery(sql2);
            ResultSet rs2 = agreedao.getResultSet();
            Long cnt=0L;
            while(rs2.next()){
                cnt = rs2.getLong(1);
                break;
            }
            if(cnt!=0)
            {
                out.println("already givelike");
            }
            else {
                UserDao userdao = new UserDao();
                userdao.connect();
                User user = userdao.selectById(userId);
                user.setScore(user.getScore() + 1);


                Agree agree=new Agree();
                agree.setAnswerId(answerId);
                agree.setUserId(LoginUser.getId());
                agreedao.insert(agree);


                userdao.connect();
                userdao.update(user);
                out.println("successfully");

            }
        }catch(Exception e)
        {
            System.out.println("error");
           e.getMessage() ;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            giveLike(request,response);
        }catch(Exception e)
        {
            e.getMessage();
            System.out.println("error-post");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            giveScore(request,response);
        }catch(Exception e)
        {
            e.getMessage();
            System.out.println("error-get");
        }
    }
}
