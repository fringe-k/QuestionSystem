
import qs.common.*;
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

        String sql = "select name from User";
        DAO dao = new DAO();
        dao.excuteQuery(sql);

        System.out.print(dao.toJsonArray().toJSONString());

    }
}
