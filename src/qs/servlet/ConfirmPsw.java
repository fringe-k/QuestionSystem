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

@WebServlet("/psw")
public class ConfirmPsw extends HttpServlet

{
    UserDao userDao = new UserDao();

    /*用户修改密码*/
    private void confirmPassword(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        response.setContentType("text");
        PrintWriter out = response.getWriter();
        String  mail=request.getParameter("email");
        System.out.println(mail);

        try {
            userDao.connect();
            User user = userDao.selectByMail(mail);

            String oldpwd = request.getParameter("oldpsw");//旧密码
            String psw = user.getPassword();

                if(oldpwd.equals(psw))/////
                {
                        System.out.println("correct");//Application
                    out.println("correct");
                }
                else
                {
                    System.out.println("旧密码输入错误");//Application
                    out.println("failed");
                }


        }catch (Exception e) {
            System.out.println("error");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try{
            confirmPassword(request,response);
        }
        catch(Exception e) {
            System.out.println("error！！！！！");
        }
    }
}
