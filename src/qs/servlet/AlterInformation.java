package qs.servlet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qs.common.*;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import static jdk.nashorn.internal.objects.Global.exit;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AlterInformation")
public class AlterInformation extends HttpServlet

        {
    UserDao userDao = new UserDao();

    /*修改用户个人信息  */
    private void updatePersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        //User user =  (User) request.getSession().getAttribute("user");
        String  mail=request.getParameter("email");
        try {
            //获取网页上输入的各项信息
            User user = userDao.selectByMail(mail);

            String phone = request.getParameter("phone");
            String username = URLDecoder.decode(request.getParameter("username"),"UTF-8");
            Long age = Long.parseLong(request.getParameter("age"));
            String email = request.getParameter("email");

            boolean isOk = true;

            User userd = userDao.selectByMail(email);
            if(userd==null) {//进行修改
                if (isOk) {
                    User userNow =new User();
                    System.out.println("信息修改");
                    System.out.println(username);

                    userNow.setPhone(phone);
                    userNow.setAge(age);
                    userNow.setMail(email);
                    userNow.setName(username);
                    userNow.setPhoto(user.getPhoto());
                    userNow.setId(user.getId());
                    userNow.setPassword(user.getPassword());
                    userNow.setRole(user.getRole());
                    userNow.setScore(user.getScore());
                    userNow.setCannotLogin(user.getCannotLogin());
                    userNow.setCannotSpeak(user.getCannotSpeak());
                    userNow.setNumOfAnswer(user.getNumOfAnswer());
                    userNow.setNumOfQuery(user.getNumOfQuery());

                    //数据库
                    UserDao userdao = new UserDao();
                    userdao.connect();
                    if (userdao.update(userNow)) {


                        JSONObject json = new JSONObject();
                        json.put("phone", phone);
                        json.put("username", username);
                        json.put("age", age);
                        json.put("mail", email);

                        out.println(json.toJSONString());

                    }
                }
            }
            else
                out.println("repeat");
        }
        catch (Exception e) {
            out.println("error");
        }

    }

            public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
            {
                try{
                    updatePersonal(request,response);

                }
                catch(Exception e) {
                    System.out.println("error1");
                }
            }

          
            public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
            {
                try{

                    String  mail=request.getParameter("email");
                    User user= userDao.selectByMail(mail);
                    String phone = user.getPhone();
                    String username= URLEncoder.encode(user.getName(),"UTF-8");
                    System.out.println(username);
                    //String username = URLDecoder.decode(request.getParameter("username"),"UTF-8");
                    Long age=user.getAge();
                    System.out.println(mail);

                    response.setContentType("text");
                    PrintWriter out = response.getWriter();

                    JSONObject json = new JSONObject();
                    json.put("phone",phone);
                    json.put("username",username);
                    json.put("age",age);
                    json.put("mail",mail);

                    out.println(json.toJSONString());

                }
                catch(Exception e) {
                    System.out.println("error2");
                }
            }
}
