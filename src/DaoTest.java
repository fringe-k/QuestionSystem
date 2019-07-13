
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import qs.common.*;

import java.io.File;

public class DaoTest {
    public static void main(String []args) throws Exception{

//        UserDao udao = new UserDao();
//        AnswerDao andao = new AnswerDao();
//        CommentDao cdao = new CommentDao();
//        SubCommentDao scdao = new SubCommentDao();
//        QuestionTypeDao qtdao = new QuestionTypeDao();
//        QuestionDao qdao = new QuestionDao();
//
//        try {
//            // test User
//            udao.connect();
//            Long uid = udao.createUser("te@test.com");
//            User user = new User();
//            user.setId(uid);
//            user.setAge(new Long(16));
//            user.setCannotLogin(new Long(0));
//            user.setPassword("123456");
//
//
////        udao.update(user);
//            udao.update(uid, "name", "liu peng fei");
//            udao.update(uid, "phone", "15396270196");
//
//            udao.delete(new Long(0));
//            user = udao.selectById(uid);
//            System.out.printf("name of User %s, mail of User %s", user.getName(), user.getmail());
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }

//        String sql = "select name from User";
//        DAO dao = new DAO();
//        dao.excuteQuery(sql);
//
////        System.out.print(dao.toJsonArray().toJSONString());
//
//        System.out.println(Class.class.getClass().getResource("/").getPath());
//
//        JsonArray jsonArray = new JsonArray();
//        jsonArray.
////        jsonArray.get(0).
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.getLong()

        System.out.println(DaoTest.class.getClass().getResource("/").getPath());
        String s = "/Users/pengfei/Desktop/javaweb/WEB-INF/classes/";
        int index = s.lastIndexOf("WEB-INF");
        s = s.substring(0,index);


        System.out.println(s);


    }
}
