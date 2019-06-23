package qs.common;

import sun.jvm.hotspot.runtime.Bytes;

import java.sql.*;


public class DAO {

    protected Connection conn;
    private String url = "jdbc:mysql://liublack.cn:3306/questionsystem"+"?useUnicode=true&characterEncoding=utf8";
    private String user = "qs";
    private String password = "123456";

    public String getUrl() { return url;}
    public String getUser() { return url;}
    public String getPassword() { return password;}

    public void setUrl(String url) { this.url = url; }
    public void setUser(String user) { this.user = user; }
    public void setPassword(String password) {this.password = password; }


    public  void connect(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to connect to database!");
        }
    }

    public Boolean insert(User user) throws Exception{
        //拼接sql语句
        String  sql = "insert into " +
                "User(id,name,age,phone,mail,password,role,score,photo,numOfQuery,numOfAnswer,cannotSpeak,cannotLogin)" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行

        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, user.getId());
            ptmt.setString(2, user.getName());
            ptmt.setLong(3, user.getAge());
            ptmt.setString(4, user.getmail());
            ptmt.setString(5, user.getPassword());
            ptmt.setLong(6, user.getRole());
            ptmt.setBytes(7, user.getPhoto());
            ptmt.setLong(8, user.getNumOfQuery());
            ptmt.setLong(9, user.getNumOfAnswer());
            ptmt.setLong(10, user.getCannotSpeak());
            ptmt.setLong(11, user.getCannotLogin());
        }catch (Exception e){
            System.err.println(e.getStackTrace());
            System.err.println("Failed to create a PreparedStatement for User!");
        }
        // 开始执行sql语
        return ptmt.execute();
    }

    public Boolean update(User user) throws Exception{
        //拼接sql语句
        String sql = "update User set name = ?, age = ?,mail = ?,password = ?,role = ?,photo = ?," +
                "numOfQuery = ?, numOfAnswer = ?, cannotSpeak = ?,cannotLogin = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行
        // 向sql语句传参
        try {
            //传参
            ptmt.setString(1, user.getName());
            ptmt.setLong(2, user.getAge());
            ptmt.setString(3, user.getmail());
            ptmt.setString(4, user.getPassword());
            ptmt.setLong(5, user.getRole());
            ptmt.setBytes(6, user.getPhoto());
            ptmt.setLong(7, user.getNumOfQuery());
            ptmt.setLong(8, user.getNumOfAnswer());
            ptmt.setLong(9, user.getCannotSpeak());
            ptmt.setLong(10, user.getCannotLogin());
            ptmt.setLong(11, user.getId());
        }catch (Exception e){
            System.err.println(e.getStackTrace());
            System.err.println("Failed to create a PreparedStatement for User!");
        }
        return ptmt.executeUpdate() > 0;
    }

    public Boolean delete(User user)throws Exception {
        String deleteSql = "delete from User where id =?";
        PreparedStatement ptmt = conn.prepareStatement(deleteSql); // 预编译SQL，减少sql执行
        ptmt.setLong(1,user.getId());
        return ptmt.executeUpdate() > 0;
    }

    public Boolean insert(Question question) throws Exception{
        //拼接sql语句
        String  sql = "insert into Question(id,userId,type,content,time,label,frequency,title)" +
                "values(?,?,?,?,?,?,?,?)";

        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行

        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, question.getId());
            ptmt.setLong(2, question.getUserID());
            ptmt.setString(3, question.getType());
            ptmt.setString(4, question.getContent());
            ptmt.setTimestamp(5, question.getTime());
            ptmt.setString(6, question.getLabel());
            ptmt.setLong(7, question.getFrequency());
            ptmt.setString(8, question.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for User!");
        }

        // 开始执行sql语
        return ptmt.execute();
    }

    //修改数据
    public Boolean update(Question question) throws Exception{
        //拼接sql语句
        String sql = "update Question" + "userId = ?,type = ?, content = ?,time = ?,label = ?,frequency = ?,title = ?"+
                "where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行
        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, question.getUserID());
            ptmt.setString(2, question.getType());
            ptmt.setString(3, question.getContent());
            ptmt.setTimestamp(4, question.getTime());
            ptmt.setString(5, question.getLabel());
            ptmt.setLong(6, question.getFrequency());
            ptmt.setString(7, question.getTitle());
            ptmt.setLong(8, question.getId());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for Question!");
        }

        //开始执行sql语句
        return ptmt.executeUpdate() > 0;

    }

    public Boolean delete(Question question)throws Exception{
        String deleteSql = "delete from Question where id=?";
        PreparedStatement ptmt = conn.prepareStatement(deleteSql); // 预编译SQL，减少sql执行
        ptmt.setLong(1,question.getId());
        return ptmt.executeUpdate() > 0;
    }

    public Boolean insert(QuestionType questionType) throws Exception{

        //拼接sql语句
        String  sql = "insert into " + "QuestionType(id,name)" +
                "values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行

        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, questionType.getId());
            ptmt.setString(2, questionType.getName());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for QuestionType!");
        }

        //开始执行sql语
        return ptmt.executeUpdate() > 0;
    }

    public Boolean update(QuestionType questionType) throws Exception{
        //拼接sql语句
        String sql = "update QuestionType set" + "name = ? where id = ?" ;
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行
        // 向sql语句传参
        try {
            //传参
            ptmt.setString(1, questionType.getName());
            ptmt.setLong(2, questionType.getId());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for QuestionType!");
        }
        return ptmt.executeUpdate() > 0;
    }

    public Boolean delete(QuestionType questionType) throws Exception {
        String deleteSql="delete from QuestionType where id= ? ";
        PreparedStatement ptmt = conn.prepareStatement(deleteSql); // 预编译SQL，减少sql执行
        ptmt.setLong(1,questionType.getId());
        return ptmt.executeUpdate() > 0;
    }

    public Boolean insert(Answer answer) throws Exception{

        //拼接sql语句
        String  sql = "insert into " + "Answer(id,userId,questionId,content,time)" +
                "values (?,?,?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行
        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, answer.getId());
            ptmt.setLong(2, answer.getUserId());
            ptmt.setLong(3, answer.getQuestionId());
            ptmt.setString(4, answer.getContent());
            ptmt.setTimestamp(5, answer.getTime());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for Answer!");
        }
        //开始执行sql语
        return ptmt.executeUpdate() > 0;

    }

    public Boolean update(Answer answer) throws Exception{
        //拼接sql语句
        String sql = "update Answer set userId = ?, questionId = ?, content = ?,time = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行
        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, answer.getUserId());
            ptmt.setLong(2, answer.getQuestionId());
            ptmt.setString(3, answer.getContent());
            ptmt.setTimestamp(4, answer.getTime());
            ptmt.setLong(5, answer.getId());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for Answer!");
        }
        return ptmt.executeUpdate() > 0;
    }

    public Boolean delete(Answer answer) throws Exception {
        String deleteSql="delete from Answer where id= ? ";
        PreparedStatement ptmt = conn.prepareStatement(deleteSql);
        ptmt.setLong(1,answer.getId());
        return ptmt.executeUpdate() > 0;
    }

    public Boolean insert(Comment comment) throws Exception{

        //拼接sql语句
        String  sql = "insert into " + "Comment(id,userId,answerId,content,time)" +
                "values(?,?,?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行

        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, comment.getId());
            ptmt.setLong(2, comment.getUserId());
            ptmt.setLong(3, comment.getAnswerId());
            ptmt.setString(4, comment.getContent());
            ptmt.setTimestamp(5, comment.getTime());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for Comment!");
        }

        return ptmt.executeUpdate() > 0;
    }

    public Boolean update(Comment comment) throws Exception{

        //拼接sql语句
        String sql = "update Comment set userId = ?,answerId = ?,content = ?, time = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行
        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, comment.getUserId());
            ptmt.setLong(2, comment.getAnswerId());
            ptmt.setString(3, comment.getContent());
            ptmt.setTimestamp(4, comment.getTime());
            ptmt.setLong(5, comment.getId());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for Comment!");
        }
        return ptmt.executeUpdate() > 0;
    }

    public Boolean delete(Comment comment) throws Exception {

        String deleteSql="delete from Comment where id = ? ";

        PreparedStatement ptmt = conn.prepareStatement(deleteSql); // 预编译SQL，减少sql执行

        ptmt.setLong(1,comment.getId());

        return ptmt.executeUpdate() > 0;

    }

    public Boolean insert(SubComment subComment) throws Exception{

        //拼接sql语句
        String  sql = "insert into " + "SubComment(id,userId,commentId,content,time)" +
                "values(?,?,?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行
        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, subComment.getId());
            ptmt.setLong(2, subComment.getUserId());
            ptmt.setLong(3, subComment.getCommentId());
            ptmt.setString(4, subComment.getContent());
            ptmt.setTimestamp(5, subComment.getTime());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for SubComment!");
        }

        return ptmt.executeUpdate() > 0;
    }

    public Boolean update(SubComment subComment) throws Exception{
        //拼接sql语句
        String sql = "update SubComment set userId = ?,commentId = ?,content = ?, time = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql); // 预编译SQL，减少sql执行
        // 向sql语句传参
        try {
            //传参
            ptmt.setLong(1, subComment.getUserId());
            ptmt.setLong(2, subComment.getCommentId());
            ptmt.setString(3, subComment.getContent());
            ptmt.setTimestamp(4, subComment.getTime());
            ptmt.setLong(5, subComment.getId());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to create a PreparedStatement for SubComment!");
        }
        return ptmt.executeUpdate() > 0;
    }

    public Boolean delete(SubComment subComment) throws Exception {
        String deleteSql = "delete from SubComment where id= ?";
        PreparedStatement ptmt = conn.prepareStatement(deleteSql); // 预编译SQL，减少sql执行
        ptmt.setLong(1,subComment.getId());
        return ptmt.executeUpdate() > 0;
    }

    public <T> int getSqlType(T value){
        if(value instanceof String)
            return Types.VARCHAR;
        else if(value instanceof Long)
            return Types.INTEGER;
        else if(value instanceof byte [])
            return Types.BLOB;
        else if(value instanceof Timestamp)
            return Types.TIMESTAMP;
        else return -1;
    }

    public <T> Boolean update(String table,Long id,String attribute,T value) throws SQLException{
        String sql = "update "+ table +"set" + attribute + " = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setObject(1,value, getSqlType(value));
        return ptmt.executeUpdate() > 0;
    }
}
