package qs.servlet;
import com.alibaba.fastjson.JSONObject;
import qs.common.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginMail")
public class LoginMail extends HttpServlet
{
    public void save(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {

        String email=request.getParameter("mail");
        String password = request.getParameter("password");


        UserDao userdao = new UserDao();
        userdao.connect();
        try
        {
            if (RSAUtil.generateKey()) {
                System.out.println("-----成功生成公私钥-------");

            }
            RSAUtil.loadKey();
            password = RSAUtil.decrypt(password);

            String psw = MD5Util.encrypt(password);
            User user =  userdao.selectByMail(email);
            if (user != null && user.getPassword().equals(psw))
            {
                if(user.getCannotLogin()==0)
            //登录成功
            {
                response.setContentType("text");
                PrintWriter out = response.getWriter();
                if(user.getRole()==0) {

                    String u="user";
                    Long id=user.getId();
                    JSONObject json = new JSONObject();
                    json.put("role", u);
                    json.put("userId", id);

                    out.println(json.toJSONString());

                    HttpSession Session = request.getSession();
                    Session.setAttribute("user", user);//将登陆成功的用户保存于session
                }
                else if(user.getRole()==1)
                {
                    String u="manager";
                    Long id=user.getId();
                    JSONObject json = new JSONObject();
                    json.put("role", u);
                    json.put("userId", id);

                    out.println(json.toJSONString());

                    HttpSession Session = request.getSession();
                    Session.setAttribute("user", user);//将登陆成功的用户保存于session
                }
            }

            else
            {
                String u="forbidden";
                Long id=user.getId();
                JSONObject json = new JSONObject();
                json.put("role", u);
                json.put("userId", id);

                response.setContentType("text");
                PrintWriter out = response.getWriter();
            }
            } else
            {
                response.setContentType("text");
                PrintWriter out = response.getWriter();
                String u="error";
                Long id=user.getId();
                JSONObject json = new JSONObject();
                json.put("role", u);
                json.put("userId", id);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {
        this.save(request,response);
    }
}
