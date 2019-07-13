package qs.servlet;
import qs.common.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/sign")
public class Sign extends HttpServlet {


    /*
    private Map<String, String> errors = new HashMap<String, String>();
    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
     */

    public boolean validate(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
    {
        response.setContentType("text");

        PrintWriter out = response.getWriter();

        String username=request.getParameter("username");
        String psw = request.getParameter("password");
        String confirmpsw = request.getParameter("confirmPassword");
        String email=request.getParameter("email");
        String phone= request.getParameter("phone");
        Long age=Long.valueOf(request.getParameter("age"));

        boolean isOk = true;

            if (!username.matches("[a-zA-Z\\d]{1,20}")&&isOk)
            {
                isOk = false;
                //errors.put("userName", "用户名不合格！！");
                out.println("username");
            }



            if (!psw.matches("[a-zA-Z\\d]{6,20}")&&isOk)
            {
                isOk = false;
                //errors.put("userPwd", "密码必须至少6位（包含数字和字母）！！");
                out.println("password");
            }

        if (confirmpsw != null&&isOk)
        {
            if (!confirmpsw.equals(psw))
            {
                isOk = false;
                out.println("passwordTwo");
            }
        }

            if (!email.matches("\\w+@\\w+(\\.\\w+)+")&&isOk)
            {
                isOk = false;
                //errors.put("email", "邮箱不是一个合法邮箱！！");
                out.println("email");
            }

        return isOk;
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {// 将注册成功的用户保存
        //按顺序分配id
        response.setContentType("text");
        PrintWriter out = response.getWriter();

        UserDao userDao = new UserDao();
        Long id = userDao.nextId(); //Long.parseLong(request.getParameter("id" ));
        Long role = 0L;//普通用户
        Long score = 0L;
        Long age = Long.parseLong(request.getParameter("age"));
        Long numOfQuery = 0L;
        Long numOfAnswer = 0L;
        Long cannotSpeak = 0L;
        Long cannotLogin = 0L;
        String name = request.getParameter("username");
        String phone = request.getParameter("phone");
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        String Photo = "";

        System.out.println(mail);

        User user = new User();
        DAO dao=new DAO();
        dao.connect();
        try {
            user.setId(id);
            user.setRole(role);
            user.setScore(score);
            user.setAge(age);
            user.setNumOfQuery(numOfQuery);
            user.setNumOfAnswer(numOfAnswer);
            user.setCannotSpeak(cannotSpeak);
            user.setCannotLogin(cannotLogin);
            user.setName(name);
            user.setPhone(phone);
            user.setMail(mail);
            user.setPassword(password);
            user.setPhoto(Photo);
            System.out.println(user.getPassword());

            if (!dao.insert(user))
            {
                System.out.println("not insert into the database");
                out.println("unsuccessfully");
                //request.setAttribute("insert_error", "未插入数据库");
                //request.getRequestDispatcher("……").forward(request, response);页面跳转
            }
            else
            {
                System.out.println("successfully insert into the database");
                out.println("successfully");
            }
        } catch (Exception e) {
            out.println("unsuccessfully");
            System.out.println("error");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
            try{
            if(validate(request,response))
            {
                System.out.println("successfully register");
                this.save(request,response);}
            else
                System.out.println("unsuccessfully register");
        }
        catch(Exception e) {
            System.out.println("error");
        }
    }

}


