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

/**
 * 文件上传
 */
@WebServlet("/uploadphoto")
public class UploadPhoto extends HttpServlet {

    UserDao userDao = new UserDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String  mail=request.getParameter("email");
        String  photo=request.getParameter("uri");
        PrintWriter out = response.getWriter();
        try {
            //获取网页上输入的各项信息
            User user = userDao.selectByMail(mail);
            System.out.println(photo);
            boolean isOk = true;

            //进行修改
            if (isOk) {

                user.setPhoto(photo);
                //数据库
                UserDao userdao = new UserDao();
                userdao.connect();
                if (userdao.update(user)) {
                    out.println("success");
                }
                else{
                    out.println("error");
                }
            }
        }
        catch (Exception e) {
            System.out.println("error!!!!!!!");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String  mail=request.getParameter("email");

        PrintWriter out = response.getWriter();
        try {
            //给网页传输photo
            User user = userDao.selectByMail(mail);

            out.println(user.getPhoto());
        }
        catch (Exception e) {
            System.out.println("error!!!!!!!");
        }
    }

}
