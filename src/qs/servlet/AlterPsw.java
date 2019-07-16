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

        String  mail=request.getParameter("email");

        //User user =  (User) request.getSession().getAttribute("user");
        try {

            User user = userDao.selectByMail(mail);
            User userNow = user;

            String oldpsw  = request.getParameter("oldpwd");//旧密码

            //String confirm = request.getParameter("confirm");//确认新密码
            String psw = user.getPassword();

            if (RSAUtil.generateKey()) {
                System.out.println("-----成功生成公私钥-------");

            }
            RSAUtil.loadKey();
            oldpsw = RSAUtil.decrypt(oldpsw);
            String oldone = MD5Util.encrypt(oldpsw);

            if(oldone.equals(psw))/////
            {
                String newpwd = request.getParameter("newpwd");//新密码

                newpwd = RSAUtil.decrypt(newpwd);
                String newone = MD5Util.encrypt(newpwd);

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
                userNow.setPassword(newone);
                userNow.setRole(user.getRole());
                userNow.setScore(user.getScore());
                userNow.setCannotLogin(user.getCannotLogin());
                userNow.setCannotSpeak(user.getCannotSpeak());
                userNow.setNumOfAnswer(user.getNumOfAnswer());
                userNow.setNumOfQuery(user.getNumOfQuery());
                try {
                    //数据库更新
                    userdao.update(userNow);
                } catch (Exception e) {
                    System.out.println("error1");
                }

                System.out.println("correct");//Application
                out.println("correct");
            }
            else
            {
                System.out.println("旧密码输入错误");//Application
                out.println("failed");
            }

//            if (newpwd.matches("[a-zA-Z\\d]{6,20}"))
//                    {
//                        if (newpwd.equals(confirm))
//                        {
//                            UserDao userdao = new UserDao();
//                            userdao.connect();
//
//                            System.out.println("修改成功");
//                            out.println("successfully");
//
//                            userNow.setPhone(user.getPhone());
//                            userNow.setAge(user.getAge());
//                            userNow.setMail(mail);
//                            userNow.setName(user.getName());
//                            userNow.setPhoto(user.getPhoto());
//                            userNow.setId(user.getId());
//                            userNow.setPassword(newpwd);
//                            userNow.setRole(user.getRole());
//                            userNow.setScore(user.getScore());
//                            userNow.setCannotLogin(user.getCannotLogin());
//                            userNow.setCannotSpeak(user.getCannotSpeak());
//                            userNow.setNumOfAnswer(user.getNumOfAnswer());
//                            userNow.setNumOfQuery(user.getNumOfQuery());
//                            try {
//                            //数据库更新
//                            userdao.update(userNow);
//                            } catch (Exception e) {
//                                System.out.println("error1");
//                            }
//                        }
//                     else {
//                        System.out.println("抱歉，密码输入不一致");//Application
//                        out.println("not same");
//                    }
//                }
//                else {
//                        out.println("unqualified");
//                    }
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
