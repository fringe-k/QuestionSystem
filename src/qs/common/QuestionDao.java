package qs.common;
import com.mysql.cj.xdevapi.SqlDataResult;

import java.sql.*;
import java.util.ArrayList;
public class QuestionDao extends DAO{

    public QuestionDao(){
        super();
    }

    public synchronized Long nextId() throws SQLException{
        String sql = "select max(id) from Question";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()) {
            return rs.getLong(1) + 1;
        }else throw new SQLException();
    }

    public synchronized Long createQuestion(Long userId) throws SQLException{
        String sql = "insert into Question(id,userId) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        Long id = nextId();
        ptmt.setLong(1,id);
        ptmt.setLong(2,userId);
        if(ptmt.executeUpdate() > 0)
            return id;
        else throw new SQLException();
    }

    public Boolean delete(Long id) throws SQLException{
        String sql = "delete from Question where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        return ptmt.executeUpdate() > 0;
    }

    public <T> Boolean update(Long id,String attribute,T value) throws SQLException{
        String sql = "update Question set " + attribute + " = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setObject(1,value, getSqlType(value));
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }

    public Question selectById(Long id) throws SQLException{
        String sql = "select * from Question where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        ResultSet rs = ptmt.executeQuery();
        Question question = null;
        while(rs.next()){
            question = new Question();
            question.setId(rs.getLong("id"));
            question.setUserId(rs.getLong("userId"));
            question.setType(rs.getLong("type"));
            question.setContent(rs.getString("content"));
            question.setTime(rs.getTimestamp("time"));
            question.setLabel(rs.getString("label"));
            question.setFrequency(rs.getLong("frequency"));
            question.setTitle(rs.getString("title"));
            question.setIsReleased(rs.getLong("isReleased"));
            question.setReward(rs.getLong("reward"));
        }
        return question;
    }

    public ArrayList<Question> selectByUserId(Long userId) throws SQLException{
        ArrayList<Question> questions = new ArrayList<Question>();
        String sql = "select * from Question where  userId = ? order by frequency desc";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,userId);
        ResultSet rs = ptmt.executeQuery();
        Question question = null;
        while(rs.next()){
            question = new Question();
            question.setId(rs.getLong("id"));
            question.setUserId(rs.getLong("userId"));
            question.setType(rs.getLong("type"));
            question.setContent(rs.getString("content"));
            question.setTime(rs.getTimestamp("time"));
            question.setLabel(rs.getString("label"));
            question.setFrequency(rs.getLong("frequency"));
            question.setTitle(rs.getString("title"));
            question.setIsReleased(rs.getLong("isReleased"));
            question.setReward(rs.getLong("reward"));
            questions.add(question);
        }
        return questions;
    }

    public ArrayList<Question> selectByTypeId(Long typeId) throws SQLException{
        ArrayList<Question> questions = new ArrayList<Question>();
        String sql = "select * from Question where  type = ? order by frequency desc";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,typeId);
        ResultSet rs = ptmt.executeQuery();
        Question question = null;
        while(rs.next()){
            question = new Question();
            question.setId(rs.getLong("id"));
            question.setUserId(rs.getLong("userId"));
            question.setType(rs.getLong("type"));
            question.setContent(rs.getString("content"));
            question.setTime(rs.getTimestamp("time"));
            question.setLabel(rs.getString("label"));
            question.setFrequency(rs.getLong("frequency"));
            question.setTitle(rs.getString("title"));
            question.setIsReleased(rs.getLong("isReleased"));
            question.setReward(rs.getLong("reward"));
            questions.add(question);
        }
        return questions;
    }

    public Long getNumOfAnswer(Long questionId) throws SQLException{
        String sql = "select count(*)  from Answer where questionId = " +
                questionId + " and isReleased = 1";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            return rs.getLong(1);
        }
        return new Long(0);
    }

//    public int search(String words) throws NullPointerException, SQLException {
//        String searchMode = getSearchMode(words);
////        String sql = String.format("select * from  Question where match (title, content) against ('%s' in boolean mode)", searchMode);
//        String sql = "select * " +
//                String.format(" match(title) against ('%s' in boolean mode) * 10 as res1 ", searchMode) +
//                String.format(" match(content) against ('%s', in boolean mode) * 3 as res2 ",searchMode) +
//                " from Question " +
//                String.format(" where match(title, content) against('%s' in boolean mode) ",searchMode) +
//                " order by (res1) + (res2) desc ";
//        System.out.println(sql);
//        excuteQuery(sql);
//        return toJsonArray().size();
//    }

    public static String getSearchMode(String words){
        String arr[] = words.split("\\s+");
        String searchMode = "";
        for (String ss:arr) {
            searchMode += String.format("+%s ", ss);
        }
        return searchMode;
    }



}
