package qs.servlet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qs.common.*;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import static jdk.nashorn.internal.objects.Global.exit;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AlterPsw")
public class AlterPsw extends HttpServlet

{
    UserDao userDao = new UserDao();

    /*用户修改密码*/
    private void changePassword(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        response.setContentType("text");
        PrintWriter out = response.getWriter();
        //找到保存到session中的登录用户
        String  mail=request.getParameter("email");
        //通过用户id重新查找用户
        try {
            User userNow = userDao.selectByMail(mail);
            User user = userDao.selectByMail(mail);
            //加载
            //Hibernate.initialize(userNow.getDepartment());
            //Hibernate.initialize(userNow.getSystemRole());

            String oldpwd = request.getParameter("oldpwd");//旧密码
            String newpwd = request.getParameter("newpwd");//新密码
            String confirm = request.getParameter("confirm");//确认新密码
            String psw = user.getPassword();

            System.out.println(oldpwd);

            if (newpwd.matches("[a-zA-Z\\d]{6,20}"))
                    {
                        //System.out.println("1111111111111111");
                        if (newpwd.equals(confirm))
                        {
                            //System.out.println("2222222222222222");
                            UserDao userdao = new UserDao();
                            userdao.connect();

                            System.out.println("修改成功");
                            out.println("successfully");

                            userNow.setPhone(user.getPhone());
                            userNow.setAge(user.getAge());
                            userNow.setMail(mail);
                            userNow.setName(user.getName());
                            userNow.setPhoto(user.getPhoto());
                            userNow.setId(user.getId());
                            userNow.setPassword(newpwd);
                            userNow.setRole(user.getRole());
                            userNow.setScore(user.getScore());
                            userNow.setCannotLogin(user.getCannotLogin());
                            userNow.setCannotSpeak(user.getCannotSpeak());
                            userNow.setNumOfAnswer(user.getNumOfAnswer());
                            userNow.setNumOfQuery(user.getNumOfQuery());
                            try {
                            //数据库

                            userdao.update(userNow);}
                             catch (Exception e) {
                            System.out.println("error1");
                        }

                        }
                     else {
                        System.out.println("抱歉，密码输入不一致");//Application
                        out.println("not same");
                    }
                }
                else {
                        out.println("unqualified");
                    }
        }catch (Exception e) {
            System.out.println("error2");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try{
            changePassword(request,response);
        }
        catch(Exception e) {
            System.out.println("error！！！！！");
        }
    }
}
