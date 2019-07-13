package qs.servlet;

import qs.common.RSAUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用于接受公钥请求的Servlet
 */
@WebServlet("/publicKey")
public class GetPublicKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            if (!RSAUtil.hasKey()) {
                RSAUtil.generateKey();
            }
            RSAUtil.loadKey();
            String publicKey = RSAUtil.getKeyString(RSAUtil.getKeys().get("publicKey"));
            out.print(publicKey);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
