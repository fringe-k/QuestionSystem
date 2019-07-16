package qs.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 注意：editor.md期望你上传图片的服务返回如下json格式的内容
 *
 *         {
 *         success : 0 | 1, //0表示上传失败;1表示上传成功
 *         message : "提示的信息",
 *         url     : "图片地址" //上传成功时才返回
 *         }
 */

@WebServlet("/uploadInEditor")
public class UploadIMGinEditor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        ArrayList<String> uris = Upload.saveFile(req, resp);
        JSONObject json = new JSONObject();

        if(uris == null){
            json.put("success", 0);
            json.put("message", "图片上传失败！");
        }else{
            json.put("success", 1);
            json.put("message", "图片上传成功！");
            json.put("url", uris.get(0));
        }
    }



}
