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

@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet

{
    UserDao userDao = new UserDao();
    /*显示用户的个人中心 */
    private void showPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //找到保存到session中的登录用户
        User user =  (User) request.getSession().getAttribute("user");
        //通过用户id重新查找用户
        Long id=user.getId();
        try {
            User userNow = userDao.selectById(id);
            //保存到request中，转发显示
            request.setAttribute("userNow", userNow);
            request.getRequestDispatcher("").forward(request, response);//显示当前用户的个人信息
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*用户修改密码*/
    private void changePassword(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        System.out.println("执行到了changePassword函数");
        PrintWriter pw = response.getWriter();
        //找到保存到session中的登录用户
        User user =  (User) request.getSession().getAttribute("user");
        //通过用户id重新查找用户
        try {
            User userNow = userDao.selectById(user.getId());
            //加载
            //Hibernate.initialize(userNow.getDepartment());
            //Hibernate.initialize(userNow.getSystemRole());

            String newpwd = request.getParameter("newpwd");//新密码
            String confirm = request.getParameter("confirm");//确认新密码

            String psw = userNow.getPassword();//得到该客户当前的字符串
            response.setContentType("text");
            PrintWriter out = response.getWriter();
            try
            {
                if(newpwd.matches("[a-zA-Z\\d]{6,20}"))
                {
                    if(newpwd.equals(confirm))
                    {
                            userNow.setPassword(newpwd);/////////////////
                            exit(request, response);
                        out.println("ok");
                        }
                    else
                    {
                        out.println("not same");//Application
                    }
                }
                else
                {
                    out.println("error");//Application
                }
            }catch(Exception e){
                out.println("error!!!!!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try{
            changePassword(request,response);
        }
        catch(Exception e) {
            System.out.println("error1");
        }
    }
}
